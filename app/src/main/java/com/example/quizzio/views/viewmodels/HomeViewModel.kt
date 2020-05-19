package com.example.quizzio.views.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzio.network.TriviaApi
import com.example.quizzio.network.TriviaUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _properties = MutableLiveData<List<TriviaUI>>()
    val properties: LiveData<List<TriviaUI>>
        get() = _properties

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
