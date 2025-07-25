package com.example.cp3406assessment2.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.state.EditBookUiState
import com.example.cp3406assessment2.ui.state.ShelfUiState
import com.example.cp3406assessment2.viewmodel.EditBookViewModel
import com.example.cp3406assessment2.viewmodel.ShelfViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShelfScreen(
    onEditButtonPressed: (Book) -> Unit,
) {
    val viewModel: ShelfViewModel = koinViewModel()
    val uiState: ShelfUiState by viewModel.shelfUiState.collectAsStateWithLifecycle()

    viewModel.retrieveShelf()

    if (uiState.books.isEmpty()) {
        Column {
            Text(
                text = "Your shelf is empty!\nAdd a book in the search menu.",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    else {
        Column {
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
                            Column {
                                Button(
                                    onClick = {  },
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "See review button"
                                    )
                                }

                                Button(
                                    onClick = { onEditButtonPressed(book) },
                                    modifier = Modifier.padding(16.dp)
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