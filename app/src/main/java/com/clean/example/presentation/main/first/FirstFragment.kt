package com.clean.example.presentation.main.first

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.clean.example.R
import com.clean.example.databinding.FragmentFirstBinding
import com.clean.example.presentation.base.BaseFragment
import com.clean.example.utils.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FirstFragment: BaseFragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding()
    private val vm: FirstViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.viewEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { proceedEvent(it) }
        }
        subscribeError(vm)

        binding.apply {
            btnGo.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }
        }
    }

    private fun proceedEvent(event: FirstViewEvent) {
        when (event) {

        }
    }
}