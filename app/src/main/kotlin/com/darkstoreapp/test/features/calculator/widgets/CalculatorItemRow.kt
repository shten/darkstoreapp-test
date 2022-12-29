package com.darkstoreapp.test.features.calculator.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorItemRow(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    title: String,
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(enabled = enabled, onClick = onClick)
            .padding(16.dp)
    )
    {
        Text(
            text = title,
            modifier = Modifier.weight(1F)
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = when (enabled) {
                true -> MaterialTheme.colors.onSurface
                false -> MaterialTheme.colors.onSurface.copy(alpha = 0.4F)
            },
        )

    }

}

