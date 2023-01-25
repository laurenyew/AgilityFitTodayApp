@file:OptIn(ExperimentalCoroutinesApi::class)

package com.laurenyew.agilityfittodayapp.utils

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Test

/**
 * Feature testing for [CountDownTimerWithPauseResume]
 */
@OptIn(ExperimentalCoroutinesApi::class)
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
            onCountDownComplete = onFinish,
            ioDispatcher = dispatcher
        )
    }


    @Test
    fun `happy path timer - timer runs and completes - onInterval and onFinish are called`() {
        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(0)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(0)

        // Exercise
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(3)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(1)
    }

    @Test
    fun `cancel - cancels timer`() {
        // Exercise
        dispatcher.scheduler.advanceTimeBy(1001L)
        testObject.cancel()
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(1)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(0)
    }

    @Test
    fun `pause - stops timer`() {
        // Exercise
        dispatcher.scheduler.advanceTimeBy(1001L)
        testObject.pause()
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(1)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(0)
    }

    @Test
    fun `pause + resume - resumes timer`() {
        // Exercise
        dispatcher.scheduler.advanceTimeBy(1001L)
        testObject.pause()
        dispatcher.scheduler.advanceUntilIdle()
        testObject.resume()
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(3)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(1)
    }

    @Test
    fun `restart - restarts timer`() {
        // Exercise
        dispatcher.scheduler.advanceTimeBy(1001L)
        testObject.restart()
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(4)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(1)
    }

    @Test
    fun `restart after completion - restarts timer`() {
        // Exercise
        dispatcher.scheduler.advanceTimeBy(1001L)
        testObject.cancel()
        testObject.restart()
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(4)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(1)
    }

    @Test
    fun `restart after cancel - restarts timer`() {
        // Exercise
        dispatcher.scheduler.advanceUntilIdle()
        testObject.restart()
        dispatcher.scheduler.advanceUntilIdle()

        // Verify
        Truth.assertThat(numIntervalCallbacks).isEqualTo(6)
        Truth.assertThat(numOnFinishCallbacks).isEqualTo(2)
    }
}