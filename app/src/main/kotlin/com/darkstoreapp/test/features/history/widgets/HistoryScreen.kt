package com.darkstoreapp.test.features.history.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darkstoreapp.test.R
import com.darkstoreapp.test.features.history.HistoryViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.history),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.height(24.dp)
                        )
                    }
                },
            )
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.history.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.no_data),
                    modifier = Modifier.wrapContentSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5F)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.history) { item ->
                        HistoryItemRow(state = item)
                        Divider()
                    }
                }
            }
        }
    }
}