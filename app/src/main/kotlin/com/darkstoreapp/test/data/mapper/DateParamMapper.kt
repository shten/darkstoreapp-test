package com.darkstoreapp.test.data.mapper

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateParamMapper @Inject constructor(

) {

    operator fun invoke(date: LocalDate): String {
        return DATE_FORMATTER.format(date)
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd")
    }
}