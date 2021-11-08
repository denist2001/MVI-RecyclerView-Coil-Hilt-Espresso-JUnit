package com.denis.mvi_recyclerview_coil_hilt_espresso_junit

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.di.NetworkModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(NetworkModule::class)
@RunWith(AndroidJUnit4::class)
abstract class BaseTestClass {
    lateinit var device: UiDevice
    lateinit var mockServer: MockWebServer

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var intentsTestRule = IntentsTestRule(HiltTestActivity::class.java)

    @get:Rule(order = 3)
    var grantPermissionRule = GrantPermissionRule.grant(
        "android.permission.INTERNET"
    )

    @Before
    fun setUp(): Unit = runBlocking {
        hiltRule.inject()
        mockServer = MockWebServer()
        mockServer.start(8080)
        launchFragment()
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    abstract fun launchFragment()
}
