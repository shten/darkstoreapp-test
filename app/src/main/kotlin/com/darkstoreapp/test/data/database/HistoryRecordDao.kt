package com.darkstoreapp.test.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryRecordDao {
    @Query("SELECT * FROM history ORDER BY lu DESC LIMIT 10")
    fun getHistory(): Flow<List<HistoryRecordEntity>>

    @Insert
    suspend fun insert(record: HistoryRecordEntity)
}