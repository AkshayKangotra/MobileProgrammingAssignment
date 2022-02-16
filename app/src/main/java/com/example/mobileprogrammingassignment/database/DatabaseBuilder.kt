package com.example.mobileprogrammingassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileprogrammingassignment.database.dao.UsersDao
import com.example.mobileprogrammingassignment.database.entity.UserDataEt

@Database(version = 1, entities = [UserDataEt::class])
abstract class DatabaseBuilder: RoomDatabase() {

    abstract fun usersDao(): UsersDao?
}