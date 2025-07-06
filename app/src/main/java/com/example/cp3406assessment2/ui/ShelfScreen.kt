package com.example.cp3406assessment2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.data.Book

@Composable
fun ShelfScreen(
    books: List<Book>,
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
                    Text(
                        text = "${books[index].title} (${books[index].year})" +
                                "\nby ${books[index].author}" +
                                "\nGenre: ${books[index].genre}" +
                                "\nPages read: ${books[index].readPageCount} / ${books[index].totalPageCount} " +
                                "(${((books[index].readPageCount.toDouble() / books[index].totalPageCount.toDouble()) * 100).toInt()}%)" +
                                "\nRating: ${books[index].rating} / 5",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}