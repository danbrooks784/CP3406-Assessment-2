package com.example.cp3406assessment2.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.cp3406assessment2.data.demoBookList
import com.example.cp3406assessment2.model.Book

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    var books: MutableList<Book> = demoBookList

    fun addBook(book: Book) {
        books.add(book)
    }
}
