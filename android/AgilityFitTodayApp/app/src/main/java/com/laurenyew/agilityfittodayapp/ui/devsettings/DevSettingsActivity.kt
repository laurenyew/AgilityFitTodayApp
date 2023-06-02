package com.laurenyew.agilityfittodayapp.ui.devsettings

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.laurenyew.agilityfittodayapp.ui.theme.AgilityFitTodayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgilityFitTodayTheme {
                DevSettingsNavHost(
                    onTopLevelBack = {
                        finish()
                    }
                )
            }
        }
    }
}
