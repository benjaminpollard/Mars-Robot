package com.example.marsrobots.models.wrappers

import com.example.marsrobots.models.PageKey
import com.example.marsrobots.models.responces.NasaResponse

class PageKeyWrapper : IPageKeyWrapper {

    fun map(remoteResponse: NasaResponse): PageKey {
        var nextPage : String? = null
        var prevPage : String? = null
        var nextPageNumber : Int? = null

        remoteResponse.collection.links.forEach {
            when(it.rel) {
               "next" -> {
                   nextPage = it.href
                   nextPageNumber = it.href.substringAfter("&page=").toInt()
               }
               "prev" -> prevPage = it.href
            }
        }
        return PageKey(nextPageNumber,nextPage, prevPage)
    }
}