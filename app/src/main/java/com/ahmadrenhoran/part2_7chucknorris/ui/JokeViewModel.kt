package com.ahmadrenhoran.part2_7chucknorris.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.part2_7chucknorris.data.model.JokesResponse
import com.ahmadrenhoran.part2_7chucknorris.data.model.Response
import com.ahmadrenhoran.part2_7chucknorris.data.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val repository: JokeRepository) : ViewModel() {

    private val _jokes = MutableLiveData<Response<JokesResponse>>()
    val jokes: LiveData<Response<JokesResponse>> get() = _jokes

    fun searchJokes(query: String) {

        viewModelScope.launch {
            _jokes.postValue(Response.Loading)
            val response = repository.searchJokes(query)
            _jokes.postValue(response)
        }
    }
}

