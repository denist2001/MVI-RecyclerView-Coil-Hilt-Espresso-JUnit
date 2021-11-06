package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment(R.layout.details_fragment) {
    private val viewModel by viewModels<DetailsViewModel>()
}