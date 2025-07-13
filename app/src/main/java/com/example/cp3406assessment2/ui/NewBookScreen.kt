package com.example.cp3406assessment2.ui

/*
    IMPORTANT: Title, author, genre, year and total page count fields are ONLY there to get it
    working before API calls are added! In the actual implementation, the user can press this
    button upon searching for a book to add it to their shelf. These fields will be automatically
    filled in.

    Also, it needs to be abstracted into the viewmodel and book repository.
 */

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.data.Book

@Composable
fun NewBookScreen(
    onAddButtonPressed: (Book) -> Unit
) {
    var title by remember { mutableStateOf("") }

    var author by remember { mutableStateOf("") }

    var yearInput by remember { mutableStateOf("") }
    val year = yearInput.toIntOrNull() ?: 0

    var totalPageCountInput by remember { mutableStateOf("") }
    val totalPageCount = totalPageCountInput.toIntOrNull() ?: 0

    var readPageCountInput by remember { mutableStateOf("") }
    val readPageCount = readPageCountInput.toIntOrNull() ?: 0

    var sliderPosition by remember { mutableFloatStateOf(1f) }
    var rating by remember { mutableIntStateOf(1) }

    var book: Book

    Column {
        InputField(
            value = title,
            onValueChange = { title = it },
            label = "Title"
        )

        InputField(
            value = author,
            onValueChange = { author = it },
            label = "Author"
        )

        InputField(
            value = yearInput,
            onValueChange = { yearInput = it },
            label = "Year"
        )

        InputField(
            value = totalPageCountInput,
            onValueChange = { totalPageCountInput = it },
            label = "Total pages"
        )

        InputField(
            value = readPageCountInput,
            onValueChange = { readPageCountInput = it },
            label = "Pages read"
        )

        Text(
            text = "Rating: ",
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

        Button(
            onClick = {
                book = Book(title, author, year, totalPageCount, readPageCount, rating)
                onAddButtonPressed(book)
            },
            modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally)
        ) {
            Text("Add book")
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(label) },
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    )
}