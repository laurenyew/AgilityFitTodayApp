package com.laurenyew.agilityfittodayapp.ui.startWorkout

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Navigation Business Logic for Start Workout Flow
 */
class StartWorkoutFlowNavManager @Inject constructor() : StartWorkoutFlowNavManagerAPI {
    override var activityBackPressLambda: (() -> Unit)? = null

    // Controls navigation
    private var _currentNavRoute = MutableStateFlow(StartWorkoutNavRoutes.SelectWorkout.route)
    override val currentNavRoute: StateFlow<String> = _currentNavRoute

    override fun updateNavRoute(newNavRoute: StartWorkoutNavRoutes) {
        _currentNavRoute.value = newNavRoute.route
    }

    override fun onBackPressed(isFromActivityOnBackPress: Boolean) {
        when (_currentNavRoute.value) {
            StartWorkoutNavRoutes.ExecuteWorkout.route -> _currentNavRoute.value =
                StartWorkoutNavRoutes.SelectWorkout.route

            else -> if (!isFromActivityOnBackPress) {
                activityBackPressLambda?.invoke()
            }
        }
    }
}

interface StartWorkoutFlowNavManagerAPI {
    val currentNavRoute: StateFlow<String>

    var activityBackPressLambda: (() -> Unit)?

    fun onBackPressed(isFromActivityOnBackPress: Boolean = false)

    fun updateNavRoute(newNavRoute: StartWorkoutNavRoutes)
}