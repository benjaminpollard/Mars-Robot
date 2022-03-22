package com.example.marsrobots.models.wrappers

import com.example.marsrobots.models.NasaItemModel
import com.example.marsrobots.models.responces.NasaResponse

interface INasaItemWrapper {
    fun map(response: NasaResponse): List<NasaItemModel>
}