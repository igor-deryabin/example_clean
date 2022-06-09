package com.clean.example.presentation.main.second

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.clean.example.R
import com.clean.example.databinding.FragmentSecondBinding
import com.clean.example.presentation.base.BaseFragment
import com.clean.example.utils.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SecondFragment: BaseFragment(R.layout.fragment_second) {

    private val binding: FragmentSecondBinding by viewBinding()
    private val vm: SecondViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.viewEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { proceedEvent(it) }
        }
        subscribeError(vm)

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun proceedEvent(event: SecondViewEvent) {
        when (event) {

        }
    }
}