package com.example.cp3406assessment2.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.viewmodel.BookViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookList(modifier: Modifier = Modifier) {
    val bookViewModel: BookViewModel = koinViewModel()
    LazyColumn(
        modifier = modifier
    ) {
        items(bookViewModel.books.size) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = bookViewModel.displayBook(bookViewModel.books[index]),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}