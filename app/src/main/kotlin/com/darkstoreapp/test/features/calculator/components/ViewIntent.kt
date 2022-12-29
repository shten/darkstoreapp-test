package com.darkstoreapp.test.features.calculator.components

import com.darkstoreapp.test.domain.entity.Currency
import java.time.LocalDate

sealed interface ViewIntent {

    sealed interface Change : ViewIntent {
        data class Amount(val value: Double) : Change
        data class Date(val value: LocalDate) : Change
        data class CurrencyOriginal(val value: Currency?) : Change
        data class CurrencyTarget(val value: Currency?) : Change
    }

    sealed interface Tap : ViewIntent {
        object Date : Tap
        object CurrencyOriginal : Tap
        object CurrencyTarget : Tap
    }

    object DismissDialog : ViewIntent
}