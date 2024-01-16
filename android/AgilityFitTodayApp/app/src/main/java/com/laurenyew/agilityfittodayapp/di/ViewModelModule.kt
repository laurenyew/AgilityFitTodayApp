package com.laurenyew.agilityfittodayapp.di

import com.laurenyew.agilityfittodayapp.features.workout.manager.ExecuteWorkoutManagerAPI
import com.laurenyew.agilityfittodayapp.features.workout.manager.ExecuteWorkoutManagerImpl
import com.laurenyew.agilityfittodayapp.features.workout.start.StartWorkoutFlowNavManager
import com.laurenyew.agilityfittodayapp.features.workout.start.StartWorkoutFlowNavManagerAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Items required for view models
 */
@InstallIn(ViewModelComponent::class)
@Module
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideStartWorkoutFlowNavManagerAPI(): StartWorkoutFlowNavManagerAPI =
        StartWorkoutFlowNavManager()

    @Provides
    @ViewModelScoped
    fun provideExecuteWorkoutManager(): ExecuteWorkoutManagerAPI = ExecuteWorkoutManagerImpl()
}
