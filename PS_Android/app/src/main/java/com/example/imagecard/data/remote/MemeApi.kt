package com.example.imagecard.data.remote

import com.example.imagecard.data.remote.dto.MemeDto
import retrofit2.http.GET
//import retrofit2.http.Path

interface MemeApi {

    @GET("/get_memes")
    suspend fun getMemes():List<MemeDto>

//    @GET("/get_memes/{}")
//    suspend fun getMemesById(@Path("id") memeId:String )

}