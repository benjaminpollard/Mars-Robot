package com.example.marsrobots.ui.main

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.marsrobots.controllers.NasaPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class MainViewModel @ExperimentalPagingApi constructor(private val controller: NasaPagingSource) : ViewModel(),
    DefaultLifecycleObserver {

    @ExperimentalPagingApi
    val itemsLiveData
        get() = controller.getItems().flowOn(Dispatchers.IO).asLiveData().cachedIn(viewModelScope)

}