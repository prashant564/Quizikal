package com.example.quizzio.views.viewmodels

import androidx.lifecycle.*
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.network.Resource
import com.example.quizzio.utils.AppConstants
import kotlinx.coroutines.launch

class HomeViewModel(private val triviaRepository: TriviaRepository, private val category:String) : ViewModel(){
    val allTrivia: MutableLiveData<Resource<MutableList<TriviaUI>>> = MutableLiveData()
    var triviaPage = 1
    var allTriviaResponse: MutableList<TriviaUI>? = null

    private val _snackMsg = MutableLiveData<String>()
    val snackMsg: LiveData<String>
        get() = _snackMsg


    init {
        getAllTrivia()
        _snackMsg.value=""

    }

    private fun showErrorMessage(text: String) {
        _snackMsg.value = text
    }

    fun getAllTrivia() = viewModelScope.launch {
        allTrivia.postValue(Resource.Loading())
        try {
            val response = triviaRepository.getAllTrivia(category,triviaPage,AppConstants.TRIVIA_LIMIT)
            if (response.isSuccessful) {
                response.body()?.let {
                    triviaPage++
                    if(allTriviaResponse==null){
                        allTriviaResponse=it
                    } else {
                         val oldTrivia = allTriviaResponse
                        val newTrivia = it
                        oldTrivia?.addAll(newTrivia)
                    }
                    allTrivia.postValue(Resource.Success( allTriviaResponse?: it))
                }
            }
        }catch (e: Exception) {
            allTrivia.postValue(Resource.Failure(e.message.toString()))
        }
    }

    fun insertTrivia(triviaUI: TriviaUI)=viewModelScope.launch {
        triviaRepository.insertTrivia(triviaUI)
    }
}

