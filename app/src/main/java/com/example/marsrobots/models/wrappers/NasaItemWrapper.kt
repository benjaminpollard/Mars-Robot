package com.example.marsrobots.models.wrappers

import com.example.marsrobots.models.NasaItemModel
import com.example.marsrobots.models.responces.Items
import com.example.marsrobots.models.responces.NasaResponse

class NasaItemWrapper : INasaItemWrapper {

    override fun map(response: NasaResponse): List<NasaItemModel> {
        val items = mutableListOf<NasaItemModel>()

        response.collection.items.forEach {
            items.add(mapItems(it))
        }

        return items
    }

    private fun mapItems(remoteModel: Items): NasaItemModel {
        val data = remoteModel.data.first()

        return NasaItemModel(
                0,
                data.description,
                data.title,
                data.nasaId,
                data.dateCreated,
                remoteModel.links.first().href)

    }

}