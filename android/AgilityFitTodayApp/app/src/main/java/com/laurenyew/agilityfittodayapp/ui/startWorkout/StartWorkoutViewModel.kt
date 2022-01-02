package com.laurenyew.agilityfittodayapp.ui.startWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartWorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {
    var activityBackPressLambda: (() -> Unit)? = null

    private var _selectedWorkout = MutableStateFlow<WorkoutSequence?>(null)
    val selectedWorkout: StateFlow<WorkoutSequence?> = _selectedWorkout

    // Controls navigation
    private var _currentNavRoute = MutableStateFlow(StartWorkoutNavRoutes.SelectWorkout.route)
    val currentNavRoute: StateFlow<String> = _currentNavRoute

    // Get first sequences
    val workoutSequences: Flow<PagingData<WorkoutSequence>> =
        workoutRepository.getWorkoutSequences()
            .cachedIn(viewModelScope)

    fun onSelectWorkoutSequence(sequenceId: Long) {
        viewModelScope.launch {
            _selectedWorkout.value = workoutRepository.getWorkoutSequence(sequenceId)
            _currentNavRoute.value = StartWorkoutNavRoutes.StartWorkout.route
        }
    }

    fun onBackPressed() {
        when (_currentNavRoute.value) {
            StartWorkoutNavRoutes.StartWorkout.route -> _currentNavRoute.value =
                StartWorkoutNavRoutes.SelectWorkout.route
            StartWorkoutNavRoutes.ExecuteWorkout.route -> _currentNavRoute.value =
                StartWorkoutNavRoutes.StartWorkout.route
            else -> activityBackPressLambda?.invoke()
        }
    }
}