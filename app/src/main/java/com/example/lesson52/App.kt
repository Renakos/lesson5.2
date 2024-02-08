package com.example.lesson52

import android.app.Application
import androidx.room.Room
import com.example.lesson52.data.local.room.AppDatabase

private const val DATABASE_NAME = "football-team-database"

class App : Application() {

    companion object {

        lateinit var db: AppDatabase
            private set

    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}