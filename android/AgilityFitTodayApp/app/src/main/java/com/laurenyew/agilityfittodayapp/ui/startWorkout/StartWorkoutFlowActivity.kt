package com.laurenyew.agilityfittodayapp.ui.startWorkout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartWorkoutFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartWorkoutScreen(
                onBackButtonPressed = {
                    onBackPressed()
                }
            )
        }
    }
}