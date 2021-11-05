package com.denis.mvi_recyclerview_coil_hilt_espresso_junit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.ui.main.MainFragment

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