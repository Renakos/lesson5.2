package com.example.lesson52.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lesson52.data.local.room.converters.SetStringConverter
import com.example.lesson52.data.local.room.dao.FootballTeamsDao
import com.example.lesson52.data.local.room.entities.FootballTeam

@Database(entities = [FootballTeam::class], version = 1, exportSchema = false)
@TypeConverters(SetStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun footballTeamsDao(): FootballTeamsDao
}