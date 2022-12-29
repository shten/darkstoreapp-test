package com.darkstoreapp.test.data.database

import androidx.room.TypeConverter
import java.time.LocalDate

object JavaTimeConverters {

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: Long): LocalDate {
        return LocalDate.ofEpochDay(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(value: LocalDate): Long = value.toEpochDay()
}