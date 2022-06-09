package com.clean.example.presentation.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.clean.example.utils.extensions.showError

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    protected fun <T> subscribeError(viewModel: BaseViewModel<T>) {
        viewModel.errorEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                requireActivity().showError(it)
            }
        }
    }
}