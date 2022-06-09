package com.clean.example.presentation.main

import android.os.Bundle
import com.clean.example.databinding.ActivityMainBinding
import com.clean.example.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity: BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.viewEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let { proceedEvent(it) }
        }
        subscribeError(vm)
    }

    private fun proceedEvent(event: MainViewEvent) {
        when (event) {

        }
    }
}