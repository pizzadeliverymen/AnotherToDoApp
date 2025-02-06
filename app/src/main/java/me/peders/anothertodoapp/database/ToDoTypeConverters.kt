package me.peders.anothertodoapp.database

import androidx.room.TypeConverter
import java.util.Date

class ToDoTypeConverters {
    @TypeConverter
    fun longFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateFromLong(date: Long): Date {
        return Date(date)
    }
}