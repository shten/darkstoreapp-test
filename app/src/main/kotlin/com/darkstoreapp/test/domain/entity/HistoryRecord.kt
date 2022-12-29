package com.darkstoreapp.test.domain.entity

import java.time.LocalDate

data class HistoryRecord(
    val date: LocalDate,
    val originalCc: String,
    val originalAmount: Double,
    val targetCc: String,
    val targetAmount: Double,
)