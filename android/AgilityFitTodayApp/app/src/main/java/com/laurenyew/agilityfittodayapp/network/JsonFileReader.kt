package com.laurenyew.agilityfittodayapp.network

import android.content.Context
import com.laurenyew.agilityfittodayapp.data.models.WorkoutItem
import com.laurenyew.agilityfittodayapp.data.models.WorkoutSequence
import com.laurenyew.agilityfittodayapp.utils.WorkoutTypeConverters
import timber.log.Timber
import java.io.IOException
import kotlin.random.Random

class JsonFileReader {
    fun getBaseWorkoutSequences(context: Context): List<WorkoutSequence> {
        val jsonString: String? = try {
            context.assets.open("BaseWorkoutSequences.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Timber.e(ioException, "Failed to load base workout sequences.")
            null
        }

        return jsonString?.let {
            WorkoutTypeConverters.workoutSequenceDTOListAdapter.fromJson(jsonString)
                ?.map { seqDTO ->
                    WorkoutSequence(
                        id = Random.nextLong(),
                        name = seqDTO.name,
                        description = seqDTO.description,
                        workoutItems = seqDTO.workoutItems.map { itemDTO ->
                            WorkoutItem(
                                id = Random.nextLong(),
                                quantity = itemDTO.quantity,
                                itemBase = WorkoutTypeConverters.stringToWorkoutItemBase(itemDTO.itemBaseName)!!
                            )
                        },
                        workoutType = seqDTO.workoutType,
                        isFavorite = false
                    )
                }
        } ?: emptyList()
    }
}