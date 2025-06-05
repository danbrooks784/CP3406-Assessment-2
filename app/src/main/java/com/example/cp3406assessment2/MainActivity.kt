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
import com.example.cp3406assessment2.ui.theme.CP3406Assessment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CP3406Assessment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Book(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Book(modifier: Modifier = Modifier) {
    val books = listOf(
        "A Tale of Two Cities",
        "The Little Prince",
        "Harry Potter and the Philosopher's Stone",
        "And Then There Were None",
        "The Hobbit",
        "Alice's Adventures in Wonderland",
        "The Da Vinci Code",
        "The Catcher in the Rye",
        "Anne of Green Gables",
        "Charlotte's Web",
        "Moby Dick",
        "Frankenstein",
        "Dracula",
        "The Adventures of Sherlock Holmes",
        "Around the World in Eighty Days"
    )

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
                    text = books[index],
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}