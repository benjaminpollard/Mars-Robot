package com.example.marsrobots.services.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.marsrobots.models.PageKey

@Dao
interface PageKeysDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveKeys(key: PageKey)

    @Query("SELECT * FROM pagekey")
    suspend fun getKeys(): List<PageKey>
}