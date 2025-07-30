package com.example.cp3406assessment2

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.data.database.BookEntity
import com.example.cp3406assessment2.di.appModules
import com.example.cp3406assessment2.data.api.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin

@RunWith(AndroidJUnit4::class)
class BookRepositoryTest : Application() {
    private val bookRepository by inject<BookRepository>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }

    private var testBook1 = Book(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230,
        230, 5, "Great mystery novel", true
    )

    private var testBook2 = Book(
        "T4rwTkWd4NcC", "The Fellowship of the Ring", listOf("John Ronald Reuel Tolkien"),
        "2003-09-05", 436, 0, 0, "Still need to read", false
    )

    private var testBook1Default = Book(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230
    )

    private var testBookEntity = BookEntity(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230,
        230, 5, "Great mystery novel", true
    )

    suspend fun cleanDatabase(books: List<Book>) {
        for (book in books) {
            bookRepository.deleteBook(book)
        }
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryTestCleanDatabase_removesAllProvidedBooksFromDatabase() = runBlocking {
        bookRepository.insertBook(testBook1)
        bookRepository.insertBook(testBook2)
        cleanDatabase(listOf(testBook1, testBook2))
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks, emptyList<Book>())
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositorySearchByQuery_returnsNetworkResultListOfBooks() = runBlocking {
        when (val result = bookRepository.searchByQuery("and then there were none")) {
            is NetworkResult.Success -> {
                assertEquals(result.data[0], testBook1Default)
            }
            is NetworkResult.Error -> {
                assertEquals(
                    result.error.toString(),
                    "Field 'items' is required for type with serial name 'com.example.cp3406assessment2.data.api.APIResponse', but it was missing at path: $"
                )
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryGetBooksFromDatabase_returnsAllBooksFromDatabase() = runBlocking {
        bookRepository.insertBook(testBook1)
        bookRepository.insertBook(testBook2)
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks[0], testBook1)
        assertEquals(allBooks[1], testBook2)

        cleanDatabase(listOf(testBook1, testBook2))
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryGetUnfinishedBooks_returnsAllUnfinishedBooksFromDatabase() = runBlocking {
        bookRepository.insertBook(testBook1)
        bookRepository.insertBook(testBook2)
        val unfinishedBooks = bookRepository.getUnfinishedBooks().first()
        assertEquals(unfinishedBooks[0], testBook2)

        cleanDatabase(listOf(testBook1, testBook2))
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryInsertBook_insertsBookToDatabase() = runBlocking {
        bookRepository.insertBook(testBook1)
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks[0], testBook1)

        cleanDatabase(listOf(testBook1))
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryDeleteBook_removesBookFromDatabase() = runBlocking {
        bookRepository.insertBook(testBook1)
        bookRepository.deleteBook(testBook1)
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks, emptyList<Book>())
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryUpdateBook_updatesBookInDatabase() = runBlocking {
        bookRepository.insertBook(testBook1Default)
        bookRepository.updateBook(testBook1)
        val allBooks = bookRepository.getBooksFromDatabase().first()
        assertEquals(allBooks[0], testBook1)

        cleanDatabase(listOf(testBook1))
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryGetBookById_returnsBookWithGivenId() = runBlocking {
        bookRepository.insertBook(testBook1)
        val book = bookRepository.getBookById(testBook1.id)
        assertEquals(book, testBook1)

        cleanDatabase(listOf(testBook1))
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryConvertBookEntityToBook_returnsBook() = runBlocking {
        val convertedBookEntity = bookRepository.convertBookEntityToBook(testBookEntity)
        assertEquals(testBook1, convertedBookEntity)
    }

    @Test
    @Throws(Exception::class)
    fun bookRepositoryConvertBookToBookEntity_returnsBookEntity() = runBlocking {
        val convertedBook = bookRepository.convertBookToBookEntity(testBook1)
        assertEquals(testBookEntity, convertedBook)
    }
}