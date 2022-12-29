package com.darkstoreapp.test.data.api

import com.darkstoreapp.test.data.dto.CurrencyDtoApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NbuService {
    @GET("NBUStatService/v1/statdirectory/exchange?json")
    suspend fun fetchList(
        @Query("date") date: String
    ): Response<List<CurrencyDtoApi>>
}