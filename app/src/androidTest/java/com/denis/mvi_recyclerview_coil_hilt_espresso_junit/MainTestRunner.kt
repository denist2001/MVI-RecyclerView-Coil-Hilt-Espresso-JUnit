package com.denis.mvi_recyclerview_coil_hilt_espresso_junit

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(ApplicationTestClass::class)
class MainTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MainTestRunner_Application::class.java.name, context)
    }
}