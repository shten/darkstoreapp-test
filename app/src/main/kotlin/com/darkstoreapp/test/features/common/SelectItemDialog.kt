package com.darkstoreapp.test.features.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SelectItemDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    data: List<String>,
    onItemSelected: (index: Int) -> Unit,
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(6.dp),
        buttons = {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var addDivider = false
                if (!title.isNullOrEmpty()) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(18.dp)
                    )
                    addDivider = true
                }

                if (addDivider) {
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
                LazyColumn {
                    itemsIndexed(data) { index, item ->
                        Text(
                            text = item,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .heightIn(min = 42.dp)
                                .fillMaxWidth()
                                .clickable {
                                    onItemSelected.invoke(index)
                                    onDismissRequest()
                                }
                                .padding(16.dp)
                        )
                        Divider()
                    }
                }
            }
        }
    )
}