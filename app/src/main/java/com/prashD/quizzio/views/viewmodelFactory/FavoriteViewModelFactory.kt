package com.prashD.quizzio.views.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prashD.quizzio.repository.TriviaRepository
import com.prashD.quizzio.views.viewmodels.FavoriteViewModel

class FavoriteViewModelFactory(private val triviaRepository: TriviaRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(triviaRepository) as T
    }
}