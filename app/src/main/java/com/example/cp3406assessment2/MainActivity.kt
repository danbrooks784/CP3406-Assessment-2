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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cp3406assessment2.model.Book
import com.example.cp3406assessment2.ui.AppViewModel
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
                        ShelfScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun ShelfScreen(
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    Shelf(
        books = appViewModel.books.sortedBy{ it.calculatePercentageRead(it) }
    )
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