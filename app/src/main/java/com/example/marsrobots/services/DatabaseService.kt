package com.example.marsrobots.services

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marsrobots.models.NasaItemModel
import com.example.marsrobots.models.PageKey
import com.example.marsrobots.services.dao.NasaItemDoa
import com.example.marsrobots.services.dao.PageKeysDao

@Database(entities = [NasaItemModel::class,PageKey::class], version = 1)
abstract class DatabaseService : RoomDatabase() {
    abstract fun nasaItemDao(): NasaItemDoa
    abstract fun pageKeyDao(): PageKeysDao
}