package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()

}