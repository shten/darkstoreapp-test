package com.darkstoreapp.test.data.repository

import com.darkstoreapp.test.data.database.HistoryRecordDao
import com.darkstoreapp.test.data.mapper.HistoryRecordMapper
import com.darkstoreapp.test.di.IoDispatcher
import com.darkstoreapp.test.domain.entity.HistoryRecord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CurrencyLocalDataSource {
    fun getHistory(): Flow<List<HistoryRecord>>
    suspend fun addHistoryRecord(record: HistoryRecord)
}

internal class CurrencyLocalDataSourceImpl @Inject constructor(
    private val mapper: HistoryRecordMapper,
    private val dao: HistoryRecordDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CurrencyLocalDataSource {
    override fun getHistory(): Flow<List<HistoryRecord>> {
        return dao.getHistory()
            .map { list -> list.map(mapper::invoke) }
            .flowOn(dispatcher)
    }

    override suspend fun addHistoryRecord(record: HistoryRecord) {
        withContext(dispatcher) {
            dao.insert(mapper.invoke(record))
        }
    }

}