package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.testUtils

import android.view.View
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.awaitility.Awaitility
import org.awaitility.core.ConditionTimeoutException
import org.hamcrest.Matcher
import org.junit.Assert
import java.util.concurrent.TimeUnit

fun waitUntilViewWithId(
    viewId: Int,
    timeout: Long = 3,
    matcher: Matcher<View>
) {
    try {
        Awaitility.await().atMost(timeout, TimeUnit.SECONDS).untilAsserted {
            onView(withId(viewId)).check(matches(matcher))
        }
    } catch (e: ConditionTimeoutException) {
        Assert.fail("View with id: $viewId doesn't match $matcher in $timeout seconds")
    }
}

fun waitUntilProgressBarAppears(
    viewId: Int,
    timeoutInMs: Long = 3
) {
    try {
        getUiObject(viewId).waitForExists(timeoutInMs)
    } catch (e: ConditionTimeoutException) {
        Assert.fail("View with id: $viewId doesn't appear in $timeoutInMs seconds")
    }
}

fun waitUntilProgressBarDisappears(
    viewId: Int,
    timeoutInMs: Long = 3
) {
    try {
        getUiObject(viewId).waitUntilGone(timeoutInMs)
    } catch (e: ConditionTimeoutException) {
        Assert.fail("View with id: $viewId doesn't disappear in $timeoutInMs seconds")
    }
}

fun getUiObject(viewId: Int): UiObject {
    val resourceId = getTargetContext().resources.getResourceName(viewId)
    val selector = UiSelector().resourceId(resourceId)
    return UiDevice.getInstance(getInstrumentation()).findObject(selector)
}