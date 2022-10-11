package com.example.memecardapp.di

import com.example.memecardapp.common.Constants
import com.example.memecardapp.data.remote.MemesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    fun provideRetrofitBuilder():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit : Retrofit):MemesService = retrofit.create(MemesService::class.java)


}