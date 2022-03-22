package com.example.marsrobots.services

import com.example.marsrobots.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseNetworkService : IBaseNetworkService {
    override fun serviceConstructor(ServiceToCon: Class<*>?): Any {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .build()
        return retrofit.create(ServiceToCon)
    }
}
