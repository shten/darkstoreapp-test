package com.darkstoreapp.test.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HistoryRecordEntity::class], version = 1)
@TypeConverters(JavaTimeConverters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun historyRecordDao(): HistoryRecordDao
}