package com.example.marsrobots.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PageKey(
    val nextPageNumber: Int?,
    val next: String?,
    val previous: String?,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
)