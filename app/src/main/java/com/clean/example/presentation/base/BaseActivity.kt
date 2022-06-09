package com.clean.example.presentation.base

import androidx.appcompat.app.AppCompatActivity
import com.clean.example.utils.extensions.showError

abstract class BaseActivity : AppCompatActivity() {

    protected fun <T> subscribeError(viewModel: BaseViewModel<T>) {
        viewModel.errorEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                showError(it)
            }
        }
    }
}