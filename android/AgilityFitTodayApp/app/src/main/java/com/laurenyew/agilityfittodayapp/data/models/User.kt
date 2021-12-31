package com.laurenyew.agilityfittodayapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Long,
    val name: String,
    val age: Int, // age in years
    val sex: String,
    val weight: Double,
    val isDog: Boolean
)