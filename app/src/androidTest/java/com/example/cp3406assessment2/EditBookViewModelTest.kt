package com.example.cp3406assessment2

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.di.appModules
import com.example.cp3406assessment2.viewmodel.EditBookViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class EditBookViewModelTest: Application() {
    private val editBookViewModel by inject<EditBookViewModel>()
    private val bookRepository by inject<BookRepository>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }

    private var testBook = Book(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230,
        230, 5, "Great mystery novel"
    )

    private var testBook1Default = Book(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230
    )

    suspend fun cleanDatabase(books: List<Book>) {
        for (book in books) {
            bookRepository.deleteBook(book)
        }
    }

    @Test
    @Throws(Exception::class)
    fun viewModelEditBook_updatesUiStateBook() = runBlocking {
        editBookViewModel.editBook(testBook)
        val uiState = editBookViewModel.editBookUiState
        assertEquals(uiState.value.book, testBook)
    }

    @Test
    @Throws(Exception::class)
    fun viewModelSaveNewBook_updatesUiStateAndAddsBookToDatabase() = runBlocking {
        editBookViewModel.editBook(testBook)
        editBookViewModel.saveNewBook(230, 5, "Great mystery novel")
        val uiState = editBookViewModel.editBookUiState
        assertEquals(uiState.value.book, testBook)
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks[0], testBook)

        bookRepository.cleanDatabase()
    }

    @Test
    @Throws(Exception::class)
    fun viewModelUpdateBook_updatesUiStateAndUpdatesBookInDatabase() = runBlocking {
        bookRepository.insertBook(testBook1Default)
        editBookViewModel.editBook(testBook)
        editBookViewModel.updateBook(230, 5, "Great mystery novel")
        val uiState = editBookViewModel.editBookUiState
        assertEquals(uiState.value.book, testBook)
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks[0], testBook)

        bookRepository.cleanDatabase()
    }

    @Test
    @Throws(Exception::class)
    fun viewModelRemoveBook_removesBookFromDatabase() = runBlocking {
        bookRepository.insertBook(testBook)
        editBookViewModel.editBook(testBook)
        editBookViewModel.removeBook()
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks, emptyList<Book>())
    }
}