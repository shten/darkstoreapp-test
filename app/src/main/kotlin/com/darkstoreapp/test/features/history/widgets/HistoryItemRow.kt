package com.darkstoreapp.test.features.history.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkstoreapp.test.features.history.components.HistoryRecordState

@Composable
fun HistoryItemRow(
    state: HistoryRecordState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = String.format("%s = %s", state.original, state.target),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = state.date,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}