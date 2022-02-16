package com.example.mobileprogrammingassignment.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobileprogrammingassignment.database.dao.UsersDao
import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import javax.inject.Singleton

@Database(version = 1, entities = [UserDataEt::class])
abstract class DatabaseBuilder: RoomDatabase() {
    private var instance: DatabaseBuilder? = null


    @Synchronized
    open fun getInstance(context: Context): DatabaseBuilder? {
        if (instance == null) {
            instance = Room.databaseBuilder(context.applicationContext,
                    DatabaseBuilder::class.java, "user_database").fallbackToDestructiveMigration()
                    .build()
        }
        return instance
    }

    abstract fun usersDao(): UsersDao?
}