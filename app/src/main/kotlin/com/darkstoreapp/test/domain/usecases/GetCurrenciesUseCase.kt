package com.darkstoreapp.test.domain.usecases

import com.darkstoreapp.test.domain.CurrencyRepository
import com.darkstoreapp.test.domain.entity.Currency
import java.time.LocalDate
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(date: LocalDate): Result<List<Currency>> {
        return currencyRepository.getCurrencies(date)
    }
}