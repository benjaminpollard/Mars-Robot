package com.example.marsrobots.services

interface IBaseNetworkService {
    fun serviceConstructor(ServiceToCon: Class<*>?): Any
}
