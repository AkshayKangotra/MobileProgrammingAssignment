package com.example.mobileprogrammingassignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataEt: List<UserDataEt>)

    @Query("SELECT * FROM users")
    fun getOfflineUsers(): Flow<List<UserDataEt>>
}