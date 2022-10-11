package com.example.memecardapp.di

import android.content.Context
import androidx.room.Room
import com.example.memecardapp.common.Constants
import com.example.memecardapp.data.database.MemeDatabase
import com.example.memecardapp.data.database.RoomDao
import com.example.memecardapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApiService(retrofit:Retrofit):ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context):MemeDatabase =
        Room.databaseBuilder(context,MemeDatabase::class.java,"memesDB").build()

    @Provides
    fun providesMemeDao(memeDatabase : MemeDatabase):RoomDao = memeDatabase.memeDao()

}