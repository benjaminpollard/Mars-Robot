package com.example.marsrobots

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.marsrobots.controllers.NasaPagingSource
import com.example.marsrobots.controllers.StringResolverController
import com.example.marsrobots.models.wrappers.INasaItemWrapper
import com.example.marsrobots.models.wrappers.IPageKeyWrapper
import com.example.marsrobots.models.wrappers.NasaItemWrapper
import com.example.marsrobots.models.wrappers.PageKeyWrapper
import com.example.marsrobots.services.BaseNetworkService
import com.example.marsrobots.services.DatabaseService
import com.example.marsrobots.services.IBaseNetworkService
import com.example.marsrobots.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

object Bootstrap {

    private lateinit var databaseService : DatabaseService

    @ExperimentalPagingApi
    @JvmStatic
    fun start(myApplication: Application) {
        databaseService = Room.databaseBuilder(myApplication, DatabaseService::class.java, "nasaDB")
            .fallbackToDestructiveMigration()
            .build()

        startKoin {
            androidContext(myApplication)
            modules(listOf(viewModelsModule, controllerModule, servicesModule, mappersModule))
        }
    }

    @ExperimentalPagingApi
    private val controllerModule = module {
        factory { StringResolverController( ApplicationInstance.getContext() ) }
        factory { NasaPagingSource(get(), databaseService, get(), get()) }
    }

    private val servicesModule = module {
        single { BaseNetworkService() } bind IBaseNetworkService::class
    }

    @ExperimentalPagingApi
    private val viewModelsModule = module {
        viewModel { MainViewModel(get()) }
    }

    private val mappersModule = module {
        single { NasaItemWrapper() } bind INasaItemWrapper::class
        single { PageKeyWrapper() } bind IPageKeyWrapper::class


    }
}