package com.example.mobileprogrammingassignment.injection

import android.content.Context
import androidx.room.Room
import com.example.mobileprogrammingassignment.database.DatabaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): DatabaseBuilder {
        return Room.databaseBuilder(
            context,
            DatabaseBuilder::class.java,
            "app_db.db"
        ).build()
    }

}