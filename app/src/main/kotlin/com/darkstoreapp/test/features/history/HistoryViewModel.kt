package com.darkstoreapp.test.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkstoreapp.test.domain.usecases.GetHistoryUseCase
import com.darkstoreapp.test.features.history.components.HistoryRecordStateMapper
import com.darkstoreapp.test.features.history.components.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    getHistoryUseCase: GetHistoryUseCase,
    historyRecordStateMapper: HistoryRecordStateMapper
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ViewState(loading = true))
    val viewState: StateFlow<ViewState> = mutableViewState.asStateFlow()

    init {
        getHistoryUseCase()
            .map { list -> list.map(historyRecordStateMapper::invoke) }
            .onEach { list ->
                emitState { it.copy(loading = false, history = list) }
            }.launchIn(viewModelScope)
    }

    private fun emitState(@Suppress("LeakingThis") reduce: (ViewState) -> ViewState) {
        mutableViewState.update(reduce)
    }
}