package com.darkstoreapp.test.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "history")
data class HistoryRecordEntity(
    @PrimaryKey val lu: Long,
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "original_cc") val originalCc: String,
    @ColumnInfo(name = "original_amount") val originalAmount: Double,
    @ColumnInfo(name = "target_cc") val targetCc: String,
    @ColumnInfo(name = "target_amount") val targetAmount: Double,
)