package com.example.mobileprogrammingassignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobileprogrammingassignment.database.entity.UserDataEt

@Dao
interface UsersDao {
    @Insert
    suspend fun insertAll(userDataEt: List<UserDataEt>):Long

    @Query("SELECT * FROM users")
    fun getOfflineUsers(): List<UserDataEt>
}