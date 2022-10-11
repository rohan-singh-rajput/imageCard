package com.example.memecardapp.data.remote.interfaces

import com.example.memeapp.data.network.apiData.memeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface MemeEndpoint {
    @GET("/get_memes")
    fun getMemes(): Call<Response<memeResponse>>
}