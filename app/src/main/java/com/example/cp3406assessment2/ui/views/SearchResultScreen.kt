package com.example.cp3406assessment2.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.state.SearchUiState
import com.example.cp3406assessment2.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    query: String,
    navigateBack: () -> Unit,
    onAddButtonPressed: (Book) -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    viewModel.searchQuery(query)

    val uiState: SearchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Searching for $query") },
                actions = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Go back button"
                        )
                    }
                }
            )
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
                                Text(
                                    text = viewModel.displayResultInfo(result),
                                    modifier = Modifier.padding(16.dp).fillMaxWidth(0.7f)
                                )
                                Button(
                                    onClick = {
                                        onAddButtonPressed(result)
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