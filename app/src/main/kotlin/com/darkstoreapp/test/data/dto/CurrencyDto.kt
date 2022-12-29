package com.darkstoreapp.test.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyDtoApi(
    @Json(name = "cc") val cc: String,
    @Json(name = "r030") val currencyCode: Int,
    @Json(name = "rate") val rate: Double,
    @Json(name = "txt") val title: String
)