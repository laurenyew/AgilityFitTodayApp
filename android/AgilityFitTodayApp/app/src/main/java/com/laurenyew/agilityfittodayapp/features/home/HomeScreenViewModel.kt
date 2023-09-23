package com.laurenyew.agilityfittodayapp.features.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.laurenyew.agilityfittodayapp.features.workout.start.StartWorkoutFlowActivity
import timber.log.Timber

class HomeScreenViewModel : ViewModel() {
    fun openStartWorkoutFlow(context: Context) {
        Timber.d("Open Start Workout Flow")
        val intent = Intent(context, StartWorkoutFlowActivity::class.java)
        context.startActivity(intent)
    }
}
