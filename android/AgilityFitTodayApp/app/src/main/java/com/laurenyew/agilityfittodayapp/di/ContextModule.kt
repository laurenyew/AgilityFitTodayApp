package com.laurenyew.agilityfittodayapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import timber.log.Timber
import java.util.concurrent.CancellationException

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    private var applicationCoroutineScope: CoroutineScope? = null

    @Provides
    fun providesContext(application: Application): Context = application.applicationContext

    @Provides
    fun providesSharedPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences("agilityfitsharedprefs", Context.MODE_PRIVATE)

    /**
     * This application scope lives for the life of the application
     */
    @Provides
    fun providesApplicationCoroutineScope(): CoroutineScope {
        /**
         * If the scope hasn't been created or has been cancelled, create the scope
         */
        if (applicationCoroutineScope == null || applicationCoroutineScope?.isActive == false) {
            applicationCoroutineScope = CoroutineScope(Job() + Dispatchers.IO)
            Timber.d("Creating application scope")
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
            /**
             * On background, cancel application scope
             */
            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop() {
                if (applicationCoroutineScope?.isActive == true) {
                    applicationCoroutineScope?.cancel(CancellationException("App Backgrounded"))
                    Timber.d("App is backgrounded. Cancelling application scope.")
                }
            }
        })
        return applicationCoroutineScope!!
    }
}
