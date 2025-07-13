package com.example.cp3406assessment2.ui

/*
    TODO: Input validation
*/

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
    onSearch: (String, String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf("title") }
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Search for a book",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        TextField(
            value = query,
            onValueChange = { query = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(query, filter) }
            ),
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                Button(
                    onClick = { isExpanded = !isExpanded }
                ) {
                    Text(
                        text = "Search by: $filter",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Filter button"
                    )
                }

                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    DropdownMenuItem(
                        text = { Text("Title") },
                        onClick = { filter = "title" }
                    )

                    DropdownMenuItem(
                        text = { Text("Author") },
                        onClick = { filter = "author" }
                    )

                    DropdownMenuItem(
                        text = { Text("Year") },
                        onClick = { filter = "year" }
                    )
                }
            }

            Button(
                onClick = { onSearch(query, filter) }
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