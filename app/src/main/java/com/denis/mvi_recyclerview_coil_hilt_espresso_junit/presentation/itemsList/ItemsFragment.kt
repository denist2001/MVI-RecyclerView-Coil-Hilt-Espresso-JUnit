package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.R
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment(R.layout.items_fragment) {

    private lateinit var binding: ItemsFragmentBinding
    private val viewModel by viewModels<ItemsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsFragmentBinding.bind(view)
    }
}