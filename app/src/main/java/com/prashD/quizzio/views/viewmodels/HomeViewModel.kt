package com.prashD.quizzio.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashD.quizzio.network.Resource
import com.prashD.quizzio.repository.TriviaRepository
import com.prashD.quizzio.utils.AppConstants
import com.prashD.quizzio.views.ui.TriviaUI
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val triviaRepository: TriviaRepository, private val category: String) :
    ViewModel() {
    val allTrivia: MutableLiveData<Resource<MutableList<TriviaUI>>> = MutableLiveData()
    var triviaPage = 1
    var allTriviaResponse: MutableList<TriviaUI>? = null

    private val _snackMsg = MutableLiveData<String>()
    val snackMsg: LiveData<String>
        get() = _snackMsg


    init {
        getAllTrivia()
        _snackMsg.value = ""

    }

    private fun showErrorMessage(text: String) {
        _snackMsg.value = text
    }

    fun getAllTrivia() = viewModelScope.launch {
        allTrivia.postValue(Resource.Loading())
                triviaRepository.getAllTrivia(category, triviaPage, AppConstants.TRIVIA_LIMIT)
                    .catch { e ->
                        allTrivia.postValue(Resource.Failure(e.message.toString()))
                    }
                    .collect { response ->
                        response.body().let {
                            triviaPage++
                            if (allTriviaResponse == null) {
                                allTriviaResponse = it
                            } else {
                                val oldTrivia = allTriviaResponse
                                val newTrivia = it
                                if (newTrivia != null) {
                                    oldTrivia?.addAll(newTrivia)
                                }
                            }
                            allTrivia.postValue(Resource.Success(allTriviaResponse ?: it!!))
                        }
                    }
    }

    fun insertTrivia(triviaUI: TriviaUI) = viewModelScope.launch {
        triviaRepository.insertTrivia(triviaUI)
    }
}

