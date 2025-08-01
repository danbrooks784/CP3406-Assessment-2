package com.example.cp3406assessment2.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.state.ShelfUiState
import com.example.cp3406assessment2.viewmodel.ShelfViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShelfScreen(
    navigateToHome: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToShelf: () -> Unit,
    onEditButtonPressed: (Book) -> Unit,
) {
    val viewModel: ShelfViewModel = koinViewModel()
    val uiState: ShelfUiState by viewModel.shelfUiState.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navigateToHome() }
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home button"
                        )
                    }
                    IconButton(
                        onClick = { navigateToSearch() }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search button"
                        )
                    }
                    IconButton(
                        onClick = { navigateToShelf() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.List,
                            contentDescription = "Shelf button"
                        )
                    }
                }
            }
        },

        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (uiState.books.isEmpty()) {
                Text(
                    text = "Your shelf is empty!\nAdd a book in the search menu.",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally)
                )
            } else {
                Text(
                    text = "Your shelf (${uiState.books.size} books):",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )

                LazyColumn(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    items(uiState.books) { book ->
                        Card(
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                        ) {
                            Row {
                                Text(
                                    text = viewModel.displayBookInfo(book),
                                    modifier = Modifier.padding(16.dp).fillMaxWidth(0.7f)
                                )
                                Button(
                                    onClick = { onEditButtonPressed(book) },
                                    modifier = Modifier.padding(16.dp).align(Alignment.CenterVertically)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit button"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}