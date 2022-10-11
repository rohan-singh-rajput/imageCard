package com.example.memecardapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.memeapp.data.network.apiData.Meme

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeme(meme : List<Meme>)

    @Query("SELECT * FROM meme_table")
    suspend fun getMemes():List<Meme>

    @Delete
    suspend fun deleteMeme(meme : Meme)
}