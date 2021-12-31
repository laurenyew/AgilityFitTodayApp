package com.laurenyew.agilityfittodayapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laurenyew.agilityfittodayapp.data.models.User

@Dao
interface UserDao {
    @Query("SELECT * from user WHERE id = :id")
    suspend fun getUser(id: Long): User?

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}