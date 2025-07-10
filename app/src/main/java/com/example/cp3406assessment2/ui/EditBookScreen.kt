package com.example.cp3406assessment2.ui

/*
    TODO: Input validation
*/

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cp3406assessment2.data.Book

@Composable
fun EditBookScreen(
    book: Book,
    onSaveButtonPressed: (Book) -> Unit,
    onDeleteButtonPressed: (Book) -> Unit
) {
    var readPageCountInput by remember { mutableStateOf("${book.readPageCount}") }
    val readPageCount = readPageCountInput.toIntOrNull() ?: 0

    var sliderPosition by remember { mutableFloatStateOf(book.rating.toFloat()) }
    var rating by remember { mutableIntStateOf(book.rating) }

    Column {
        Text(
            text = book.title,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp).align(alignment = Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = readPageCountInput,
                onValueChange = { readPageCountInput = it },
                singleLine = true,
                label = { Text("Pages read") },

            )

            Column {
                Text(
                    text = "Out of",
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                Text(
                    text = "${book.totalPageCount}",
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

        Row(
            modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    book.readPageCount = readPageCount
                    book.rating = rating
                    onSaveButtonPressed(book)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Save book")
            }

            Button(
                onClick = {
                    onDeleteButtonPressed(book)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Delete book")
            }
        }
    }
}