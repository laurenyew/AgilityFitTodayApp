package com.laurenyew.agilityfittodayapp.ui.startWorkout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartWorkoutFlowActivity : AppCompatActivity() {
    private val startWorkoutViewModel: StartWorkoutFlowViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hook in onbackpressed
        startWorkoutViewModel.activityBackPressLambda = {
            onBackPressed()
        }

        setContent {
            StartWorkoutScreen(
                startWorkoutViewModel
            )
        }
    }

    override fun onBackPressed() {
        startWorkoutViewModel.onBackPressed(true)
        super.onBackPressed()
    }
}
