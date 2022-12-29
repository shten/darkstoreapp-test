package com.darkstoreapp.test.features.calculator.components

import com.darkstoreapp.test.domain.entity.Currency
import com.darkstoreapp.test.domain.entity.HistoryRecord
import java.time.LocalDate

data class ViewState(
    val loading: Boolean = false,
    val date: LocalDate = LocalDate.now(),
    val currencies: List<Currency> = emptyList(),

    val original: CurrencyState? = null,
    val target: CurrencyState? = null,
    val dialogState: DialogState? = null
) {
    data class CurrencyState(
        val currency: Currency,
        val amount: Double
    )
}


sealed interface DialogState {
    object Date : DialogState
    object CurrencyOriginal : DialogState
    object CurrencyTarget : DialogState
    object Error : DialogState
}


fun ViewState.asHistoryRecord(): HistoryRecord? {
    if (original == null) return null
    if (target == null) return null
    if (original.amount == 0.0) return null
    return HistoryRecord(
        date = date,
        originalCc = original.currency.cc,
        originalAmount = original.amount,
        targetCc = target.currency.cc,
        targetAmount = target.amount
    )
}



