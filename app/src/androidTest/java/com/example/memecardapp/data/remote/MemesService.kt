package com.example.memecardapp.data.remote


import com.example.memeapp.data.network.apiData.memeResponse
import com.example.memecardapp.data.remote.interfaces.MemeEndpoint
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class  MemesService  constructor(private val retrofit : Retrofit): MemeEndpoint {
    private val  endpoint by lazy {  retrofit.create(MemeEndpoint::class.java)  }
    override fun getMemes() : Call<Response<memeResponse>>   {
        return endpoint.getMemes()
    }
}