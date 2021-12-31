package com.laurenyew.agilityfittodayapp.ui.dashboard

import android.content.Context
import android.widget.Toast

sealed class DashboardOption(
    val name: String,
    val onClick: (Context) -> Unit
) {
    object Calendar : DashboardOption(
        name = "Calendar of Workouts",
        onClick = {
            Toast.makeText(it, "Open Calendar", Toast.LENGTH_SHORT).show()
        }
    )

    object Explore : DashboardOption(
        name = "Explore Workouts",
        onClick = {
            Toast.makeText(it, "Open Explore", Toast.LENGTH_SHORT).show()
        }
    )

    object Schedule : DashboardOption(
        name = "Schedule Workouts",
        onClick = {
            Toast.makeText(it, "Open Schedule", Toast.LENGTH_SHORT).show()
        }
    )

    object StartWorkout : DashboardOption(
        name = "Start Workout",
        onClick = {
            Toast.makeText(it, "Open Start Workout Flow", Toast.LENGTH_SHORT).show()
        }
    )

    object CreateWorkout : DashboardOption(
        name = "Create a Workout",
        onClick = {
            Toast.makeText(it, "Open Workout Creation Flow", Toast.LENGTH_SHORT).show()
        }
    )

    object Badges : DashboardOption(
        name = "Badges",
        onClick = {
            Toast.makeText(it, "Open Badges", Toast.LENGTH_SHORT).show()
        }
    )
}
