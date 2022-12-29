package com.darkstoreapp.test.data.repository

import com.darkstoreapp.test.data.api.NbuService
import com.darkstoreapp.test.data.mapper.CurrencyMapper
import com.darkstoreapp.test.data.mapper.DateParamMapper
import com.darkstoreapp.test.di.IoDispatcher
import com.darkstoreapp.test.domain.entity.Currency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

interface CurrencyRemoteDataSource {
    suspend fun fetchCurrenciesList(date: LocalDate): Result<List<Currency>>
}

internal class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val service: NbuService,
    private val currencyMapper: CurrencyMapper,
    private val dateParamMapper: DateParamMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CurrencyRemoteDataSource {
    override suspend fun fetchCurrenciesList(date: LocalDate): Result<List<Currency>> {
        return withContext(dispatcher) {
            kotlin.runCatching {
                service.fetchList(dateParamMapper(date))
            }.fold(
                onSuccess = { currencyMapper(it) },
                onFailure = { Result.failure(it) }
            )
        }
    }

}