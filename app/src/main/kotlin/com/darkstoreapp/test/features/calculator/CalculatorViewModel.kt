package com.darkstoreapp.test.features.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkstoreapp.test.domain.entity.Currency
import com.darkstoreapp.test.domain.usecases.AddHistoryRecordUseCase
import com.darkstoreapp.test.domain.usecases.GetCurrenciesUseCase
import com.darkstoreapp.test.features.calculator.components.DialogState
import com.darkstoreapp.test.features.calculator.components.ViewIntent
import com.darkstoreapp.test.features.calculator.components.ViewIntent.Change
import com.darkstoreapp.test.features.calculator.components.ViewIntent.Change.*
import com.darkstoreapp.test.features.calculator.components.ViewIntent.Tap
import com.darkstoreapp.test.features.calculator.components.ViewState
import com.darkstoreapp.test.features.calculator.components.asHistoryRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val addHistoryRecordUseCase: AddHistoryRecordUseCase
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = mutableViewState.asStateFlow()

    private val mutableIntent: MutableSharedFlow<ViewIntent> = MutableSharedFlow(replay = 0)

    private val processorCurrencies = MutableStateFlow<List<Currency>>(emptyList())
    private val processorDate = MutableStateFlow(LocalDate.now())
    private val processorOriginalCurrency = MutableStateFlow<Currency?>(null)
    private val processorOriginalAmount = MutableStateFlow(1.0)
    private val processorTargetCurrency = MutableStateFlow<Currency?>(null)


    private val flowDate = processorDate.asStateFlow()

    private val flowOriginalState = combine(
        processorOriginalCurrency.asStateFlow(),
        processorOriginalAmount.asStateFlow()
    ) { currency, amount ->
        currency?.let {
            ViewState.CurrencyState(
                currency = it,
                amount = amount
            )
        }
    }

    private val flowTargetState = combine(
        processorOriginalCurrency.asStateFlow(),
        processorTargetCurrency.asStateFlow(),
        processorOriginalAmount.asStateFlow()
    ) { from, to, amount ->
        return@combine when {
            from == null -> null
            to == null -> null
            else -> ViewState.CurrencyState(
                currency = to,
                amount = BigDecimal.valueOf(from.rate / to.rate * amount)
                    .setScale(2, RoundingMode.HALF_UP)
                    .toDouble()
            )
        }
    }

    init {
        combine(
            processorCurrencies.asStateFlow(),
            flowDate,
            flowOriginalState,
            flowTargetState
        ) { currencies, date, original, target ->
            emitState { state ->
                state.copy(
                    currencies = currencies,
                    date = date,
                    original = original,
                    target = target
                )
            }
        }.launchIn(viewModelScope)

        flowDate.map { date ->
            emitState { it.copy(loading = true) }
            getCurrenciesUseCase.invoke(date)
        }.onEach { result ->
            emitState { it.copy(loading = false) }
            result.onSuccess {
                val list = it.sortedBy { item -> item.cc }
                processorCurrencies.emit(list)
            }.onFailure { throwable ->
                emitState { it.copy(dialogState = DialogState.Error) }
                Timber.e(throwable)
            }
        }.launchIn(viewModelScope)

        viewState.map { state -> state.asHistoryRecord() }
            .filterNotNull()
            .distinctUntilChanged()
            .onEach { historyRecord ->
                addHistoryRecordUseCase.invoke(historyRecord)
            }.launchIn(viewModelScope)

        viewModelScope.launch {
            mutableIntent.collect(::handleIntent)
        }
    }


    private fun handleIntent(intent: ViewIntent) {
        when (intent) {
            is Change -> intent.handle()
            is Tap -> intent.handle()
            ViewIntent.DismissDialog -> emitState { it.copy(dialogState = null) }
        }
    }

    private fun Change.handle() {
        when (this) {
            is Date -> processorDate.tryEmit(value)
            is Amount -> processorOriginalAmount.tryEmit(value)
            is CurrencyOriginal -> processorOriginalCurrency.tryEmit(value)
            is CurrencyTarget -> processorTargetCurrency.tryEmit(value)
        }
    }

    private fun Tap.handle() {
        when (this) {
            Tap.Date -> emitState { it.copy(dialogState = DialogState.Date) }
            Tap.CurrencyOriginal -> emitState { it.copy(dialogState = DialogState.CurrencyOriginal) }
            Tap.CurrencyTarget -> emitState { it.copy(dialogState = DialogState.CurrencyTarget) }
        }
    }

    fun emitIntent(intent: ViewIntent) {
        viewModelScope.launch { mutableIntent.emit(intent) }
    }

    private fun emitState(@Suppress("LeakingThis") reduce: (ViewState) -> ViewState) {
        mutableViewState.update(reduce)
    }
}