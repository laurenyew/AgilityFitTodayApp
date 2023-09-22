package com.laurenyew.agilityfittodayapp.di

import android.app.Application
import androidx.room.Room
import com.laurenyew.agilityfittodayapp.data.db.AgilityFitTodayDatabase
import com.laurenyew.agilityfittodayapp.data.db.DatabaseManager
import com.laurenyew.agilityfittodayapp.data.db.FavoriteDatabaseProvider
import com.laurenyew.agilityfittodayapp.data.db.UserDatabaseProvider
import com.laurenyew.agilityfittodayapp.data.db.WorkoutDatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAgilityFitTodayDatabase(application: Application): AgilityFitTodayDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            AgilityFitTodayDatabase::class.java,
            "agilty-fit-today-database"
        )
            .build()

    @Singleton
    @Provides
    fun provideDatabaseManager(database: AgilityFitTodayDatabase): DatabaseManager =
        DatabaseManager(database)

    @Singleton
    @Provides
    fun provideUserDatabaseProvider(databaseManager: DatabaseManager): UserDatabaseProvider =
        databaseManager

    @Singleton
    @Provides
    fun provideWorkoutDatabaseProvider(databaseManager: DatabaseManager): WorkoutDatabaseProvider =
        databaseManager

    @Singleton
    @Provides
    fun provideFavoriteDatabaseProvider(databaseManager: DatabaseManager): FavoriteDatabaseProvider =
        databaseManager
}
