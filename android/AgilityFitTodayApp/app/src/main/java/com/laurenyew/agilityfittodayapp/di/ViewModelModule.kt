package com.laurenyew.agilityfittodayapp.di

import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutFlowNavManager
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutFlowNavManagerAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

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
}