@file:OptIn(ExperimentalCoroutinesApi::class)

package com.laurenyew.agilityfittodayapp.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Feature testing for [CountDownTimerWithPauseResume]
 */
class CountDownTimerWithPauseResumeTest {
    private lateinit var testObject: CountDownTimerWithPauseResume

    private val dispatcher = StandardTestDispatcher()
    private val testTotalTime = 3000L
    private val testIntervalTime = 1000L

    private var numIntervalCallbacks = 0
    private var numOnFinishCallbacks = 0

    private val onInterval: ((Long) -> Unit) = {
        numIntervalCallbacks++
    }

    private val onFinish: (() -> Unit) = {
        numOnFinishCallbacks++
    }

    @Before
    fun setup() {
        numIntervalCallbacks = 0
        numOnFinishCallbacks = 0

        testObject = CountDownTimerWithPauseResume(
            testTotalTime,
            testIntervalTime,
            onIntervalTick = onInterval,
            onCountDownComplete = onFinish
        )
    }

    @Test
    fun `happy path timer - timer runs and completes - onInterval and onFinish are called`() =
        runTest {
            testObject
        }

    @Test
    fun `cancel - cancels timer`() {

    }

    @Test
    fun `pause + resume - resumes timer`() {

    }

    @Test
    fun `restart - restarts timer`() {

    }
}