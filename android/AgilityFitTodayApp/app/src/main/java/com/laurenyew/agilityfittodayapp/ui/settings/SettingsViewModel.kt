package com.laurenyew.agilityfittodayapp.ui.settings

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laurenyew.agilityfittodayapp.BuildConfig
import com.laurenyew.agilityfittodayapp.ui.devsettings.DevSettingsActivity
import com.laurenyew.agilityfittodayapp.ui.startWorkout.StartWorkoutFlowActivity
import timber.log.Timber

class SettingsViewModel : ViewModel() {
    val shouldShowDevSettingsButton: Boolean = BuildConfig.DEBUG

    fun openDevSettings(context: Context) {
        Timber.d("Open DevSettings")
        val intent = Intent(context, DevSettingsActivity::class.java)
        context.startActivity(intent)
    }
}