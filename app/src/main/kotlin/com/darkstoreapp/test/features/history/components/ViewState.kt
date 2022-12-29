package com.darkstoreapp.test.features.history.components


data class ViewState(
    val loading: Boolean = false,
    val history: List<HistoryRecordState> = emptyList()
)

data class HistoryRecordState(
    val date: String,
    val original: String,
    val target: String
)
