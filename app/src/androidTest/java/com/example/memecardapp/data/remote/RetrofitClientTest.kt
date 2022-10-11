package com.example.memecardapp.data.network

import com.example.memecardapp.common.Constants
import com.example.memecardapp.data.remote.MemesService
import com.example.memecardapp.data.remote.RetrofitClient
import com.example.memecardapp.di.AppModule
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {
    @Test
    fun testRetrofitInstance() {
        val instance : Retrofit = AppModule.provideRetrofitBuilder()
        assert(instance.baseUrl().toString() == Constants.BASE_URL)
    }

    @Test
    fun testMemesService(){
        val service = MemesService(RetrofitClient().retrofit)
        val response = service.getMemes().execute()
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }
}