package com.example.marsrobots.controllers

import android.content.Context

class StringResolverController(private val context: Context) {

    fun get(id : Int) : String {
        return context.getString(id)
    }

}