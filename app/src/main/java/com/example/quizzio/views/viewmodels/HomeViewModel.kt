package com.example.quizzio.views.viewmodels

import androidx.lifecycle.*
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val triviaRepository: TriviaRepository, private val category:String) : ViewModel(){
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val allTrivia: MutableLiveData<Resource<List<TriviaUI>>> = MutableLiveData()

    private val _snackMsg = MutableLiveData<String>()
    val snackMsg: LiveData<String>
        get() = _snackMsg


    init {
        _snackMsg.value=""
        getAllTrivia()
    }

    private fun showErrorMessage(text: String) {
        _snackMsg.value = text
    }

    fun getAllTrivia() =
        viewModelScope.launch {
        allTrivia.postValue(Resource.Loading())
        val trivia = triviaRepository.getAllTrivia(category)
        trivia.enqueue(object : Callback<List<TriviaUI>> {
            override fun onFailure(call: Call<List<TriviaUI>?>, t: Throwable) {
                allTrivia.postValue(Resource.Failure(t.message.toString()))
            }
            override fun onResponse(
                call: Call<List<TriviaUI>?>,
                response: Response<List<TriviaUI>?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        allTrivia.postValue(Resource.Success(it))
                    }
                }
            }
        })
    }

    fun insertTrivia(triviaUI: TriviaUI)=viewModelScope.launch {
        triviaRepository.insertTrivia(triviaUI)
    }
}

