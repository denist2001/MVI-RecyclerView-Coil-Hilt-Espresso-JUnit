package com.denis.mvi_recyclerview_coil_hilt_espresso_junit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}