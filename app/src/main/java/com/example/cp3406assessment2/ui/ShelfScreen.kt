package com.example.cp3406assessment2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.data.Book

@Composable
fun ShelfScreen(
    books: List<Book>,
    onEditButtonPressed: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        LazyColumn(
            modifier = modifier
        ) {
            items(books.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row {
                        Text(
                            text = "${books[index].title} (${books[index].year})" +
                                    "\nby ${books[index].author}" +
                                    "\nGenre: ${books[index].genre}" +
                                    "\nPages read: ${books[index].readPageCount} / ${books[index].totalPageCount} " +
                                    "(${((books[index].readPageCount.toDouble() / books[index].totalPageCount.toDouble()) * 100).toInt()}%)" +
                                    "\nRating: ${books[index].rating} / 5",
                            modifier = Modifier.padding(16.dp).fillMaxWidth(0.7f)
                        )

                        Button(
                            onClick = { onEditButtonPressed(books[index]) },
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