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
class StartWorkoutFlowViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val navManager: StartWorkoutFlowNavManagerAPI
) : ViewModel(), StartWorkoutFlowNavManagerAPI by navManager {

    private var _selectedWorkout = MutableStateFlow<WorkoutSequence?>(null)
    val selectedWorkout: StateFlow<WorkoutSequence?> = _selectedWorkout

    // Get first sequences
    val workoutSequences: Flow<PagingData<WorkoutSequence>> =
        workoutRepository.getWorkoutSequences()
            .cachedIn(viewModelScope)

    // Workout Execution

    fun restartWorkout() {

    }

    fun pauseWorkout() {

    }

    fun resumeWorkout() {

    }

    fun finishWorkout() {

    }

    // Flow Navigation

    /**
     * Select a Workout to Preview
     */
    fun onSelectWorkoutSequence(sequenceId: Long) {
        viewModelScope.launch {
            _selectedWorkout.value = workoutRepository.getWorkoutSequence(sequenceId)
            navManager.updateNavRoute(StartWorkoutNavRoutes.StartWorkout)
        }
    }

    /**
     * Choose to start the previewed workout
     */
    fun onStartWorkoutFromPreviewScreen() {
        navManager.updateNavRoute(StartWorkoutNavRoutes.ExecuteWorkout)
    }
}