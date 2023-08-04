package com.article.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.article.appperformancesample",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }
    @Test
    fun rotateAnimation() = benchmarkRule.measureRepeated(
        packageName = "com.article.appperformancesample" ,
        metrics = listOf(FrameTimingMetric()) ,
        iterations = 5 ,
        startupMode = StartupMode.COLD
    ){
        pressHome()
        startActivityAndWait()
        testRotationAnimation()

    }
}

fun MacrobenchmarkScope.testRotationAnimation() {
    val rotateLeftBtn = device.findObject(By.text("rotate left"))
    val rotateRightBtn = device.findObject(By.text("rotate right"))

    device.waitForIdle()

    rotateRightBtn.click()

    device.waitForIdle(1000)

    rotateLeftBtn.click()

    device.waitForIdle(1000)
}