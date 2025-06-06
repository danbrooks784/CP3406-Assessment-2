package com.example.cp3406assessment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.model.Book
import com.example.cp3406assessment2.ui.theme.CP3406Assessment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CP3406Assessment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Shelf(
                        books = demoBooks(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun demoBooks(): List<Book> {
    val books = listOf<Book>(
        Book("A Tale of Two Cities", "Charles Dickens", "Historical", 1859, 392, 392, 4),
        Book("The Little Prince", "Antoine de Saint-Exupéry", "Fantasy", 1943, 260),
        Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Fantasy", 1997, 224, 224, 2),
        Book("And Then There Were None", "Agatha Christie", "Mystery", 1939, 272, 272, 5),
        Book("The Hobbit", "J. R. R. Tolkien", "Fantasy", 1937, 310, 128),
        Book("Alice's Adventures in Wonderland", "Lewis Carroll", "Fantasy", 1865, 96),
        Book("The Catcher in the Rye", "J. D. Salinger", "Fiction", 1951, 234, 80),
        Book("Anne of Green Gables", "Lucy Maud Montgomery", "Fiction", 1908, 299, 299, 3),
        Book("Moby Dick", "Herman Melville", "Adventure", 1851, 635, 635, 4),
        Book("Frankenstein", "Mary Shelley", "Science fiction", 1818, 280, 280, 5),
        Book("Dracula", "Bram Stoker", "Horror", 1897, 354),
        Book("The Adventures of Sherlock Holmes", "Arthur Conan Doyle", "Mystery", 1982, 307, 307, 4),
    )

    return books
}

@Composable
fun Shelf(books: List<Book>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(256.dp)
    ) {
        items(books.size) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${books[index].title}\nby ${books[index].author}" +
                            "\nGenre: ${books[index].genre}" +
                            "\nPublished in: ${books[index].year}" +
                            "\nPages read: ${books[index].readPageCount} / ${books[index].totalPageCount}" +
                            "\nRating: ${books[index].rating} / 5",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}