package com.example.quizzio.views.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.views.ui.TriviaUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    fun getAllFavTrivia() = triviaRepository.getAllFavTrivia()
    fun deleteTrivia(triviaUI: TriviaUI)=viewModelScope.launch {
        triviaRepository.deleteTrivia(triviaUI)
    }
}
