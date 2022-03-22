package com.example.marsrobots.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NasaItemModel (
        @PrimaryKey(autoGenerate = true) var id : Int,
        var description : String,
        var title : String,
        var nasaId : String,
        var dateCreated : String,
        var image : String
)