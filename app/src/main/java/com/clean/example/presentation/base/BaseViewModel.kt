package com.clean.example.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.example.utils.extensions.Event
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {
    private val _viewEvent = MutableLiveData<Event<T>>()
    val viewEvent: LiveData<Event<T>> = _viewEvent

    private val _errorEvent = MutableLiveData<Event<String>>()
    val errorEvent: LiveData<Event<String>> = _errorEvent

    protected fun postEvent(event: T) {
        _viewEvent.value = Event(event!!)
    }

    protected fun postError(message: String?) {
        _errorEvent.value = Event(message!!)
    }

    protected fun launch(run: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                run()
            } catch (e: Throwable) {

            }
        }
    }
}