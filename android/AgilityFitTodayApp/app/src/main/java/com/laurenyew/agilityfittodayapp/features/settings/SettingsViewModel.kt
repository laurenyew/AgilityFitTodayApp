package com.laurenyew.agilityfittodayapp.features.settings

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.laurenyew.agilityfittodayapp.devsettings.DevSettingsActivity
import timber.log.Timber

class SettingsViewModel : ViewModel() {
    val shouldShowDevSettingsButton: Boolean = true

    fun openDevSettings(context: Context) {
        Timber.d("Open DevSettings")
        val intent = Intent(context, DevSettingsActivity::class.java)
        context.startActivity(intent)
    }
}
