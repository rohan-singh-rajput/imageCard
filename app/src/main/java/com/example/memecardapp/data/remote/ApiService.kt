package com.example.memecardapp.data.remote

import com.example.memeapp.data.network.apiData.memeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/get_memes")
    suspend fun getAllMemes(): Response<memeResponse>
}