package com.troy.pokemon.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {
    //basic handler for handle exception
    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleException(throwable)
    }

    protected open fun handleException(throwable: Throwable) {
        Log.e("BaseViewModel", "handleException : $throwable")
    }
}