package com.example.marsrobots.controllers

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marsrobots.models.NasaItemModel
import com.example.marsrobots.models.wrappers.PageKeyWrapper
import com.example.marsrobots.models.wrappers.INasaItemWrapper
import com.example.marsrobots.services.DatabaseService
import com.example.marsrobots.services.IBaseNetworkService
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class NasaPagingSource(
        private val networkService: IBaseNetworkService,
        private val databaseService: DatabaseService,
        private val objectMapper: INasaItemWrapper,
        private val keyMapper: PageKeyWrapper,
) {

    fun getItems(): Flow<PagingData<NasaItemModel>> {

        return Pager(
                PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = false
                ),
                //cant Use DI because of ExperimentalPagingApi and koin not playing nice
                remoteMediator = NasaRemoteMediator(
                        networkService,
                        objectMapper,
                        keyMapper,
                        databaseService
                ),
                pagingSourceFactory = { databaseService.nasaItemDao().getAll() }
        ).flow
    }
}