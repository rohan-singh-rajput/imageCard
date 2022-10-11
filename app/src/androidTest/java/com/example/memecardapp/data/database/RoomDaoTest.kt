package com.example.memecardapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.memeapp.data.network.apiData.Meme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RoomDaoTest{
    private lateinit var database : MemeDatabase
    private lateinit var dao : RoomDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            MemeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.memeDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertMeme() = runBlocking{
        val memeItem = Meme(id = 0, box_count = 2,height=20,"Dog meme","https://i.imgflip.com/1bij.jpg", width = 20)
        val memeItem2 = Meme(id = 0, box_count = 2,height=20,"Dog meme","https://i.imgflip.com/1bij.jpg", width = 20)
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem2)
        dao.insertMeme(listMeme)
        val getMemeItems = dao.getMemes()
        val byName = dao.getMemes()
        assert(byName.size == getMemeItems.size)
    }

    @Test
    fun getMemes() = runBlocking {
        val memeItem = Meme(id = 0, box_count = 2,height=20,"Dog meme","https://i.imgflip.com/1bij.jpg", width = 20)
        val memeItem2 = Meme(id = 0, box_count = 2,height=20,"Dog meme","https://i.imgflip.com/1bij.jpg", width = 20)
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem2)
        dao.insertMeme(listMeme)
        val totalItems = dao.getMemes()
        assert(totalItems.isNotEmpty())
    }

    @Test
    fun deleteMemes() = runBlocking {
        val memeItem = Meme(id = 0, box_count = 2,height=20,"Dog meme","https://i.imgflip.com/1bij.jpg", width = 20)
        val memeItem2 = Meme(id = 0, box_count = 2,height=20,"Dog meme","https://i.imgflip.com/1bij.jpg", width = 20)
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem2)
        dao.deleteMeme(memeItem2)
        assert(listMeme.contains(memeItem2))
    }
}



