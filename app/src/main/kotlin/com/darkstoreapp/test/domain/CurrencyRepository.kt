package com.darkstoreapp.test.domain

import com.darkstoreapp.test.domain.entity.Currency
import com.darkstoreapp.test.domain.entity.HistoryRecord
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface CurrencyRepository {
    suspend fun getCurrencies(date: LocalDate): Result<List<Currency>>
    fun getHistory(): Flow<List<HistoryRecord>>
    suspend fun addHistoryRecord(record: HistoryRecord)
}