package com.example.memeapp.data.network.apiData

import androidx.room.Entity
import androidx.room.PrimaryKey

//model class defined
@Entity(tableName = "meme_table")
data class Meme(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val box_count: Int,
    val height: Int,
    val name: String,
    val url: String,
    val width: Int
)