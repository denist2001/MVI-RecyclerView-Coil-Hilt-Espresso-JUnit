package com.denis.mvi_recyclerview_coil_hilt_espresso_junit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}