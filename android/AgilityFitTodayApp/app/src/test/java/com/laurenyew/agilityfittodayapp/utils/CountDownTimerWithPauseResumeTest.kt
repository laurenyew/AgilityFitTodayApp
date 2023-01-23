@file:OptIn(ExperimentalCoroutinesApi::class)

package com.laurenyew.agilityfittodayapp.utils

import com.google.common.truth.Truth
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

    private val expectedNumIntervals = 3
    private val expectedNumFinishEvents = 1

    @Before
    fun setup() {
        numIntervalCallbacks = 0
        numOnFinishCallbacks = 0

        testObject = CountDownTimerWithPauseResume(
            testTotalTime,
            testIntervalTime,
            onIntervalTick = onInterval,
            onCountDownComplete = onFinish,
            ioDispatcher = dispatcher
        )
    }


    @Test
    fun `happy path timer - timer runs and completes - onInterval and onFinish are called`() =
        runTest {
            // Verify
            Truth.assertThat(numIntervalCallbacks).isEqualTo(0)
            Truth.assertThat(numOnFinishCallbacks).isEqualTo(0)

            // Exercise
            dispatcher.scheduler.advanceUntilIdle()

            // Verify
            Truth.assertThat(numIntervalCallbacks).isEqualTo(expectedNumIntervals)
            Truth.assertThat(numOnFinishCallbacks).isEqualTo(expectedNumFinishEvents)
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