package com.example.lesson52.data.local.room.converters

import androidx.room.TypeConverter

class SetStringConverter {
    @TypeConverter
    fun fromString(value: String): Set<String> {
        return value.split(",").toSet()
    }

    @TypeConverter
    fun fromSet(value: Set<String>): String {
        return value.joinToString(",")
    }
}
