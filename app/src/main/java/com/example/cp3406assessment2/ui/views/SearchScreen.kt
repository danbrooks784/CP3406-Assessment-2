package com.example.cp3406assessment2.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BookTopAppBar() { }
        },

        bottomBar = {
            BookBottomAppBar() { }
        },

        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "Search for a book",
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally)
            )

            TextField(
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearch(query) }
                ),
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )

            Button(
                onClick = { onSearch(query) },
                modifier = Modifier.fillMaxWidth(0.7f).align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Search",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search button"
                )
            }
        }
    }
}

//            Box {
//                Button(
//                    onClick = { isExpanded = !isExpanded }
//                ) {
//                    Text(
//                        text = "Search by: $filter",
//                        modifier = Modifier.padding(horizontal = 8.dp)
//                    )
//                    Icon(
//                        Icons.Default.KeyboardArrowDown,
//                        contentDescription = "Filter button"
//                    )
//                }
//
//                DropdownMenu(
//                    expanded = isExpanded,
//                    onDismissRequest = { isExpanded = false },
//                    modifier = Modifier.fillMaxWidth(0.4f)
//                ) {
//                    DropdownMenuItem(
//                        text = { Text("Title") },
//                        onClick = { filter = "title" }
//                    )
//
//                    DropdownMenuItem(
//                        text = { Text("Author") },
//                        onClick = { filter = "author" }
//                    )
//                }
//            }