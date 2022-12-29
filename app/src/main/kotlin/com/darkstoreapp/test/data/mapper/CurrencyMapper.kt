package com.darkstoreapp.test.data.mapper

import com.darkstoreapp.test.data.dto.CurrencyDtoApi
import com.darkstoreapp.test.domain.entity.Currency
import retrofit2.Response
import javax.inject.Inject

class CurrencyMapper @Inject constructor(
) {

    operator fun invoke(response: Response<List<CurrencyDtoApi>>): Result<List<Currency>> {
        return kotlin.runCatching {
            response.body()?.map { item ->
                Currency(
                    code = item.currencyCode,
                    title = item.title,
                    cc = item.cc,
                    rate = item.rate
                )
            } ?: emptyList()
        }
    }
}