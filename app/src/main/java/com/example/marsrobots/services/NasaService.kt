package com.example.marsrobots.services


import com.example.marsrobots.models.responces.NasaResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NasaService {

    companion object {
        const val PAGE = "page"
        const val LIMIT = "limit"
    }

    @GET("search?q=mars&media_type=image")
    suspend fun getList(@Query(PAGE) offset: Int): NasaResponse

}