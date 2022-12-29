package com.darkstoreapp.test.features.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    initial: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismissed: () -> Unit
) {
    MaterialDialog(
        dialogState = rememberMaterialDialogState(initialValue = true),
        buttons = {
            positiveButton(stringResource(id = android.R.string.ok)) {
                onDismissed()
            }
            negativeButton(stringResource(id = android.R.string.cancel)) {
                onDismissed()
            }
        },
        autoDismiss = true,
        onCloseRequest = {
            it.hide()
            onDismissed()
        }
    ) {
        datepicker(
            initialDate = initial,
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = MaterialTheme.colors.surface,
                headerTextColor = MaterialTheme.colors.onSurface,
            )
        ) { date ->
            onDateSelected(date)
        }
    }
}

