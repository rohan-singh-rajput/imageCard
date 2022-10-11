package com.example.memecardapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.memeapp.data.network.apiData.Meme

@Database(entities = [Meme::class], version = 1)
abstract class MemeDatabase : RoomDatabase(){
    abstract fun memeDao() : RoomDao
}