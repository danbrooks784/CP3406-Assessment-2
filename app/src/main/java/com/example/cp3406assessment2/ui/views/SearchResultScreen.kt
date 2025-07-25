package com.example.cp3406assessment2.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.state.SearchUiState
import com.example.cp3406assessment2.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchResultScreen(
    query: String,
    onAddButtonPressed: (Book) -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    viewModel.searchQuery(query)

    val uiState: SearchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            BookTopAppBar() { }
        },

        bottomBar = {
            BookBottomAppBar() { }
        },

        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding),
        ) {
            AnimatedVisibility(
                visible = uiState.isLoading
            ) {
                CircularProgressIndicator()
            }

            AnimatedVisibility(
                visible = uiState.result.isNotEmpty()
            ) {
                LazyColumn {
                    items(uiState.result) { result ->
                        Card(
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                        ) {
                            Row {
                                var isEnabled by rememberSaveable { mutableStateOf(true) }

                                Text(
                                    text = "${result.title} (${result.year.take(4)})" +
                                            "\nby ${
                                                if (result.authors.isEmpty()) "unknown author"
                                                else (result.authors.joinToString(separator = ", "))
                                            }" +
                                            "\nPages: ${result.totalPageCount}",
                                    modifier = Modifier.padding(16.dp).fillMaxWidth(0.7f)
                                )
                                Button(
                                    enabled = isEnabled,
                                    onClick = {
                                        onAddButtonPressed(result)
                                        isEnabled = false
                                    },
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AddCircle,
                                        contentDescription = "Add button"
                                    )
                                }
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = uiState.error != null
            ) {
                Text(uiState.error ?: "")
            }
        }
    }
}