package com.darkstoreapp.test.domain.usecases

import com.darkstoreapp.test.domain.CurrencyRepository
import com.darkstoreapp.test.domain.entity.HistoryRecord
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(): Flow<List<HistoryRecord>> {
        return currencyRepository.getHistory()
    }
}