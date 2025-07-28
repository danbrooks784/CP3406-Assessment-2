package com.example.cp3406assessment2.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.state.EditBookUiState
import com.example.cp3406assessment2.viewmodel.EditBookViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookScreen(
    book: Book,
    navigateBack: () -> Unit
) {
    val viewModel: EditBookViewModel = koinViewModel()
    val uiState: EditBookUiState by viewModel.editBookUiState.collectAsStateWithLifecycle()

    viewModel.editBook(book)

    var readPageCountInput by remember { mutableStateOf("${book.readPageCount}") }
    val readPageCount = readPageCountInput.toIntOrNull() ?: 0

    var sliderPosition by remember { mutableFloatStateOf(book.rating.toFloat()) }
    var rating by remember { mutableIntStateOf(book.rating) }

    var review by remember { mutableStateOf(uiState.book.review) }

    var isButtonEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editing ${uiState.book.title}") },
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = readPageCountInput,
                    onValueChange = { readPageCountInput = it },
                    singleLine = true,
                    label = { Text("Pages read") }
                )

                Column {
                    Text(
                        text = "Out of",
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "${uiState.book.totalPageCount}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }

            Text(
                text = "Rating:",
                modifier = Modifier.padding(8.dp).align(alignment = Alignment.CenterHorizontally)
            )

            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    rating = it.toInt()
                },
                valueRange = 1f..5f,
                steps = 3,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
            )

            Text(
                text = "$rating",
                modifier = Modifier.padding(8.dp).align(alignment = Alignment.CenterHorizontally)
            )

            TextField(
                value = review,
                onValueChange = { review = it },
                singleLine = false,
                label = { Text("Review") },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.updateBook(readPageCount, rating, review)
                        isButtonEnabled = false
                    },
                    enabled = isButtonEnabled,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Save changes")
                }

                Button(
                    onClick = {
                        viewModel.removeBook()
                        isButtonEnabled = false
                    },
                    enabled = isButtonEnabled,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Remove book from shelf",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}