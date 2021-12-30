package com.laurenyew.agilityfittodayapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val estimatedTime: Long,
    //TODO
)
