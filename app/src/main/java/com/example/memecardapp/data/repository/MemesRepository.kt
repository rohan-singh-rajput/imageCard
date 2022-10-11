package com.example.memecardapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memeapp.data.network.apiData.Meme
import com.example.memeapp.data.network.apiData.memeResponse
import com.example.memecardapp.data.database.RoomDao
import com.example.memecardapp.data.remote.ApiService
import javax.inject.Inject

class MemesRepository @Inject constructor(
    private val apiService:ApiService,
    private val memeDao: RoomDao
) {
    private val memesLiveData = MutableLiveData<memeResponse>()

    val memes :LiveData<memeResponse>
    get() = memesLiveData

     suspend fun insertMemes(){
             val result = apiService.getAllMemes()
             if(result.body() != null) {
                 memesLiveData.postValue(result.body())
                 memeDao.insertMeme(result.body()!!.data.memes)
        }
    }
    
    suspend fun getMemes(){
        memeDao.getMemes()
    }


    suspend fun deleteMeme(meme:Meme){
        memeDao.deleteMeme(meme)
    }
}