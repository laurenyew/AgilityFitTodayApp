package com.laurenyew.agilityfittodayapp.ui.devsettings

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevSettingsNavHost(
                onTopLevelBack = {
                    finish()
                }
            )
        }
    }
}