package com.route.todoappc_37.database.model

import androidx.room.TypeConverter
import java.util.Date


class Converters {
    @TypeConverter
    fun toDate(milliseconds: Long): Date {
        return Date(milliseconds)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time

    }
}