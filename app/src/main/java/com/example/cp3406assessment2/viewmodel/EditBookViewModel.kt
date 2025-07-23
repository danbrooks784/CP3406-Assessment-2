package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.view.EditBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EditBookViewModel(
    private val bookRepository: BookRepository,
    bookToEdit: Book
) : ViewModel() {
    val editBookUiState = MutableStateFlow(EditBookUiState(book = bookToEdit))

    fun isPageCountValid(book: Book): Boolean {
        return book.readPageCount <= book.totalPageCount
    }

    fun saveBook() {
        if (isPageCountValid(editBookUiState.value.book)) {
            viewModelScope.launch {
                bookRepository.insertBook(editBookUiState.value.book)
            }
        }
    }

    fun removeBook() {
        viewModelScope.launch {
            bookRepository.deleteBook(editBookUiState.value.book)
        }
    }
}