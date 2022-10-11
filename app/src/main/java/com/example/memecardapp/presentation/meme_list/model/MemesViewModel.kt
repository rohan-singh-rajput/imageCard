package com.example.memecardapp.presentation.meme_list.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeapp.data.network.apiData.Meme
import com.example.memeapp.data.network.apiData.memeResponse
import com.example.memecardapp.data.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MemesViewModel @Inject constructor(
    private val memesRepository : MemesRepository
):ViewModel(){
     var  meme:Meme ?= null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            memesRepository.insertMemes()
        }
    }

    val memes : LiveData<memeResponse>
        get() = memesRepository.memes

    fun getMemesList() = viewModelScope.launch {
        memesRepository.getMemes()
    }

    fun deleteMeme(meme:Meme) = viewModelScope.launch {
        memesRepository.deleteMeme(meme)
    }
}