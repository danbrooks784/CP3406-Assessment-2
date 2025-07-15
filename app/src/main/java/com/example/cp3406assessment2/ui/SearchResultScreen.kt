package com.example.cp3406assessment2.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.view.BookUiState

@Composable
fun SearchResultScreen(
    uiState: State<BookUiState>,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = uiState.value.isLoading
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = uiState.value.searchResult.isNotEmpty()
        ) {
            LazyColumn {
                items(uiState.value.searchResult) { result ->
                    Card(
                        modifier = Modifier.padding(8.dp).fillMaxWidth()
                    ) {
                        Row {
                            var isBookInShelf by remember { mutableStateOf(false) }

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
                                enabled = !isBookInShelf,
                                onClick = {
                                    uiState.value.books.add(result)
                                    isBookInShelf = true
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
            visible = uiState.value.error != null
        ) {
            Text(uiState.value.error ?: "")
        }
    }
}