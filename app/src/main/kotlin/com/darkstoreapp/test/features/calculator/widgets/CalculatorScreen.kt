package com.darkstoreapp.test.features.calculator.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darkstoreapp.test.R
import com.darkstoreapp.test.features.calculator.CalculatorViewModel
import com.darkstoreapp.test.features.calculator.components.DialogState
import com.darkstoreapp.test.features.calculator.components.ViewIntent
import com.darkstoreapp.test.features.common.DatePickerDialog
import com.darkstoreapp.test.features.common.ErrorMessageDialog
import com.darkstoreapp.test.features.common.SelectItemDialog
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel(),
    onOpenHistory: () -> Unit
) {
    val state by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.calculator),
                    )
                },
                actions = {
                    IconButton(onClick = onOpenHistory) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }
            )
        }
    ) {

        Column {
            LinearProgressIndicator(
                color = com.darkstoreapp.test.features.common.Colors.Green500,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(if (state.loading) 1F else 0F)
            )
            CalculatorItemRow(
                enabled = !state.loading,
                title = stringResource(id = R.string.date),
                value = DateTimeFormatter.ISO_LOCAL_DATE.format(state.date),
                onClick = { viewModel.emitIntent(ViewIntent.Tap.Date) }
            )
            Divider()
            CalculatorItemRow(
                enabled = !state.loading && state.currencies.isNotEmpty(),
                title = stringResource(id = R.string.original),
                value = when (val value = state.original?.currency?.cc) {
                    null -> stringResource(id = R.string.choose_currency_hint)
                    else -> value
                },
                onClick = { viewModel.emitIntent(ViewIntent.Tap.CurrencyOriginal) }
            )

            Divider()
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.amount),
                    modifier = Modifier.weight(1F)
                )
                OutlinedTextField(
                    value = state.original?.amount?.toString() ?: "1.0",
                    onValueChange = {
                        viewModel.emitIntent(ViewIntent.Change.Amount(it.toDouble()))
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .defaultMinSize(minWidth = 50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    enabled = !state.loading,
                )
            }


            Divider()
            CalculatorItemRow(
                enabled = !state.loading && state.currencies.isNotEmpty(),
                title = stringResource(id = R.string.target),
                value = when (val value = state.target?.currency?.cc) {
                    null -> stringResource(id = R.string.choose_currency_hint)
                    else -> value
                },
                onClick = { viewModel.emitIntent(ViewIntent.Tap.CurrencyTarget) }

            )
            Divider()


            val original = state.original
            val target = state.target

            if (original != null && target != null) {
                Text(
                    text = stringResource(
                        id = R.string.result_format,
                        original.amount,
                        original.currency.cc,
                        target.amount.toString(),
                        target.currency.cc
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

            when (state.dialogState) {
                DialogState.Date -> DatePickerDialog(
                    initial = state.date,
                    onDateSelected = { viewModel.emitIntent(ViewIntent.Change.Date(it)) },
                    onDismissed = { viewModel.emitIntent(ViewIntent.DismissDialog) }
                )
                DialogState.CurrencyOriginal,
                DialogState.CurrencyTarget -> SelectItemDialog(
                    onDismissRequest = { viewModel.emitIntent(ViewIntent.DismissDialog) },
                    data = state.currencies.map { String.format("%s, %s", it.cc, it.title) },
                    onItemSelected = { index ->
                        viewModel.emitIntent(
                            when (state.dialogState) {
                                DialogState.CurrencyOriginal -> ViewIntent.Change.CurrencyOriginal(
                                    state.currencies.getOrNull(
                                        index
                                    )
                                )
                                else -> ViewIntent.Change.CurrencyTarget(
                                    state.currencies.getOrNull(
                                        index
                                    )
                                )
                            }
                        )
                    }
                )
                DialogState.Error -> ErrorMessageDialog(
                    errorMsg = stringResource(id = R.string.error_message),
                    onDismissRequest = { viewModel.emitIntent(ViewIntent.DismissDialog) }
                )
                else -> {}
            }
        }
    }
}