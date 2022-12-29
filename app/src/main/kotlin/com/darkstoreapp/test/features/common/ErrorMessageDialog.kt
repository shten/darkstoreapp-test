package com.darkstoreapp.test.features.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.darkstoreapp.test.R


@Composable
fun ErrorMessageDialog(
    errorMsg: String,
    onDismissRequest: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(id = R.string.error_title),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
            )
        },
        text = {
            Text(
                text = errorMsg,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Spacer(modifier = Modifier.weight(0.25f))
                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxHeight(),
                ) {
                    Text(text = stringResource(id = android.R.string.ok))
                }
                Spacer(modifier = Modifier.weight(0.25f))
            }

        }
    )
}