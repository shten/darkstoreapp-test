package com.darkstoreapp.test.domain.usecases

import com.darkstoreapp.test.domain.CurrencyRepository
import com.darkstoreapp.test.domain.entity.HistoryRecord
import javax.inject.Inject

class AddHistoryRecordUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(record: HistoryRecord) {
        return currencyRepository.addHistoryRecord(record)
    }
}