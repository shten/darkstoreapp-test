package com.darkstoreapp.test.data.repository

import com.darkstoreapp.test.domain.CurrencyRepository
import com.darkstoreapp.test.domain.entity.Currency
import com.darkstoreapp.test.domain.entity.HistoryRecord
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val local: CurrencyLocalDataSource,
    private val remote: CurrencyRemoteDataSource
) : CurrencyRepository {
    override suspend fun getCurrencies(date: LocalDate): Result<List<Currency>> {
        return remote.fetchCurrenciesList(date)
    }

    override fun getHistory(): Flow<List<HistoryRecord>> {
        return local.getHistory()
    }

    override suspend fun addHistoryRecord(record: HistoryRecord) {
        local.addHistoryRecord(record)
    }

}