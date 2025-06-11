package com.example.cp3406assessment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cp3406assessment2.model.Book
import com.example.cp3406assessment2.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = { BottomBar() },
                    floatingActionButton = { LogButton() },
                    modifier = Modifier.fillMaxSize()
                ) {
                    innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Shelf(
                            books = demoBooks().sortedBy{ it.calculatePercentageRead(it) },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
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
        Book("Moby Dick", "Herman Melville", "Adventure", 1851, 635, 30),
        Book("Frankenstein", "Mary Shelley", "Science fiction", 1818, 280, 280, 5),
        Book("Dracula", "Bram Stoker", "Horror", 1897, 354, 302),
        Book("The Adventures of Sherlock Holmes", "Arthur Conan Doyle", "Mystery", 1982, 307, 307, 4),
    )
    return books
}

fun findFinishedBooks(books: List<Book>): MutableList<Book> {
    val finishedBooks = mutableListOf<Book>()
    for (book in books) {
        if (book.totalPageCount == book.readPageCount) {
            finishedBooks.add(book)
        }
    }
    return finishedBooks
}

fun findUnfinishedBooks(books: List<Book>): MutableList<Book> {
    val unfinishedBooks = mutableListOf<Book>()
    for (book in books) {
        if (book.totalPageCount > book.readPageCount && book.readPageCount != 0) {
            unfinishedBooks.add(book)
        }
    }
    return unfinishedBooks
}

fun findUnreadBooks(books: List<Book>): MutableList<Book> {
    val unreadBooks = mutableListOf<Book>()
    for(book in books) {
        if(book.readPageCount == 0) {
            unreadBooks.add(book)
        }
    }
    return unreadBooks
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text("Bookshelf App") },
        actions = {
            IconButton(
                onClick = { TODO() }
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings button"
                )
            }
        }
    )
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { TODO() }
            ) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home button"
                )
            }
            IconButton(
                onClick = { TODO() }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search button"
                )
            }
            IconButton(
                onClick = { TODO() }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Shelf button"
                )
            }
        }
    }
}

@Composable
fun LogButton(modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(2.dp),
        onClick = { TODO() }
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "New Book button"
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "New Book"
        )
    }
}

@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        Text(
            text = book.displayBook(book),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Shelf(books: List<Book>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(books.size) { index ->
            BookCard(books[index])
        }
    }
}