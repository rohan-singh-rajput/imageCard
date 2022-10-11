package com.example.memecardapp.data.remote

import com.example.memeapp.data.network.apiData.memeResponse
import retrofit2.Response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService:ApiService) {
    suspend fun getAllMemes():Response<memeResponse> = apiService.getAllMemes()
}