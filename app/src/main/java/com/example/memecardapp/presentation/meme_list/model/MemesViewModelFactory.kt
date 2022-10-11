package com.example.memecardapp.presentation.meme_list.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memecardapp.data.repository.MemesRepository
import javax.inject.Inject

class MemesViewModelFactory @Inject constructor (private val memesRepository : MemesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        return MemesViewModel(memesRepository) as T
    }
}