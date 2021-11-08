package com.denis.mvi_recyclerview_coil_hilt_espresso_junit

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList.ItemsFragment
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.testUtils.*
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Test

@InternalCoroutinesApi
@HiltAndroidTest
class ProgressBarTest : BaseTestClass() {

    private lateinit var navController: NavController

    override fun launchFragment() {
        launchFragmentInHiltContainer<ItemsFragment> {
            //preparation for starting fragment
            navController = TestNavHostController(requireActivity())
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun progressBar_shouldAppearDuringStartAndDisappearWhenListDownloaded() {
        setDispatcher("item1.json", 200)
        intentsTestRule.launchActivity(null)
        waitUntilProgressBarAppears(R.id.progress_pb, 1000)
        waitUntilViewWithId(R.id.items_list_rv, 5, isDisplayed())
        waitUntilProgressBarDisappears(R.id.progress_pb, 1000)
    }

    @Test
    fun progressBar_shouldAppearDuringStartAndDisappearWhenError() {
        setDispatcher("item1.json", 200)
        intentsTestRule.launchActivity(null)
        waitUntilProgressBarAppears(R.id.progress_pb, 1000)
        waitUntilViewWithId(R.id.items_list_rv, 5, isDisplayed())
        waitUntilProgressBarDisappears(R.id.progress_pb, 1000)
    }

    @After
    fun tearDown() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationNatural()
        mockServer.shutdown()
    }

    private fun setDispatcher(fileName: String, responseCode: Int) {
        mockServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = request.path
                if (!path.isNullOrEmpty() && path.contains("item1.json")) {
                    return MockResponse()
                        .setResponseCode(responseCode)
                        .setBody(getStringFrom("item1.json"))
                }
                return MockResponse()
                    .setResponseCode(responseCode)
                    .setBody(getStringFrom(fileName))
            }
        }
    }
}