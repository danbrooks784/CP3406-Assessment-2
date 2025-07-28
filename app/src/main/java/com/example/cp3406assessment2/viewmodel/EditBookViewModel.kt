package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.ui.state.EditBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EditBookViewModel(
    private val bookRepository: BookRepository,
) : ViewModel() {
    val editBookUiState = MutableStateFlow(EditBookUiState())

    fun editBook(book: Book) {
        editBookUiState.value.book = book
    }

    fun saveNewBook(readPageCount: Int, rating: Int, review: String) {
        viewModelScope.launch {
            editBookUiState.value.book.readPageCount = readPageCount
            editBookUiState.value.book.rating = rating
            editBookUiState.value.book.review = review
            bookRepository.insertBook(editBookUiState.value.book)
        }
    }

    fun updateBook(readPageCount: Int, rating: Int, review: String) {
        viewModelScope.launch {
            editBookUiState.value.book.readPageCount = readPageCount
            editBookUiState.value.book.rating = rating
            editBookUiState.value.book.review = review
            bookRepository.updateBook(editBookUiState.value.book)
        }
    }

    fun removeBook() {
        viewModelScope.launch {
            bookRepository.deleteBook(bookRepository.getBookById(editBookUiState.value.book.id))
        }
    }
}