package com.example.quizzio.views.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.views.viewmodels.HomeViewModel

class TriviaViewModelFactory(private val triviaRepository: TriviaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(triviaRepository) as T
    }
}