package com.example.marsrobots.controllers

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.marsrobots.models.NasaItemModel
import com.example.marsrobots.models.PageKey
import com.example.marsrobots.models.wrappers.INasaItemWrapper
import com.example.marsrobots.models.wrappers.PageKeyWrapper
import com.example.marsrobots.services.DatabaseService
import com.example.marsrobots.services.IBaseNetworkService
import com.example.marsrobots.services.NasaService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class NasaRemoteMediator(
        private val service: IBaseNetworkService,
        private val objectMapper: INasaItemWrapper,
        private val keyMapper: PageKeyWrapper,
        private val database: DatabaseService
) : RemoteMediator<Int, NasaItemModel>() {

    private fun getNasaItemsService() =
        service.serviceConstructor(NasaService::class.java) as NasaService

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NasaItemModel>
    ): MediatorResult {
        return try {
            //on fresh, go back to the start, on PREPEND we don't need to worry about as we start at the start of the list
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getKey()
                }
            }

            val response = if (loadKey == null) {
                database.clearAllTables()
                getNasaItemsService().getList(1)
            } else {
                loadKey.nextPageNumber?.let {
                    getNasaItemsService().getList(it)
                }
            }

            response?.let {
                val keyToSave = keyMapper.map(it)
                val items = objectMapper.map(it)

                CoroutineScope(Dispatchers.IO).launch {
                    database.nasaItemDao().insertAll(items)
                    database.pageKeyDao().saveKeys(keyToSave)
                }
            }

            val containsNext = response?.collection?.links?.any {
                it.rel == "next"
            }
            MediatorResult.Success(endOfPaginationReached = containsNext == true)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getKey(): PageKey? {
        return database.pageKeyDao().getKeys().lastOrNull()
    }
}