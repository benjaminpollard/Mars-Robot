package com.example.marsrobots.services.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.marsrobots.models.NasaItemModel

@Dao
interface NasaItemDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<NasaItemModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: NasaItemModel)

    @Query("SELECT * FROM nasaitemmodel")
    fun getAll(): PagingSource<Int, NasaItemModel>

    @Delete
    fun delete(user: NasaItemModel)
}