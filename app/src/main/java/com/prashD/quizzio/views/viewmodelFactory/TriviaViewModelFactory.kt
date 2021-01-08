package com.prashD.quizzio.views.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prashD.quizzio.repository.TriviaRepository
import com.prashD.quizzio.views.viewmodels.HomeViewModel

class TriviaViewModelFactory(private val triviaRepository: TriviaRepository, val category: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(triviaRepository, category) as T
    }
}