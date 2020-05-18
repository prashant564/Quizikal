package com.example.quizzio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzio.network.TriviaApi
import com.example.quizzio.network.TriviaUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<TriviaUI>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<TriviaUI>>
        get() = _properties
    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getTriviaByCategory("Entertainment")
    }


    private fun getTriviaByCategory(category: String) {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getTriviaDeferred = TriviaApi.retrofitService.getAllTrivia(category)
            try {
                // this will run on a thread managed by Retrofit
                val listResult = getTriviaDeferred.await()
                _properties.value = listResult
            } catch (e: Exception) {
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
