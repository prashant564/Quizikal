package com.example.quizzio.views.viewmodels

import androidx.lifecycle.*
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.network.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val triviaRepository: TriviaRepository, private val category:String) : ViewModel(){
    val allTrivia: MutableLiveData<Resource<List<TriviaUI>>> = MutableLiveData()

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
            val response = triviaRepository.getAllTrivia(category)
            if (response.isSuccessful) {
                response.body()?.let {
                    allTrivia.postValue(Resource.Success(it))
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

