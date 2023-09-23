package com.laurenyew.agilityfittodayapp.features.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Notifications TBD"
    }
    val text: LiveData<String> = _text
}
