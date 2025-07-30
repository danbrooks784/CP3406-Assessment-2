package com.example.cp3406assessment2

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406assessment2.data.database.BookDao
import com.example.cp3406assessment2.data.database.BookDatabase
import com.example.cp3406assessment2.data.database.BookEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.io.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class BookDaoTest {
    private lateinit var bookDao: BookDao
    private lateinit var bookDatabase: BookDatabase

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        bookDatabase = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        bookDao = bookDatabase.bookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        bookDatabase.close()
    }

    private var testBook1 = BookEntity(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230,
        230, 5, "Great mystery novel", true
    )

    private var testBook2 = BookEntity(
        "-AfkybBr0ooC", "Dracula", listOf("Bram Stoker"), "1982-04-12", 97, 97, 4,
        "Nice short read", true
    )

    private var testBook3 = BookEntity(
        "T4rwTkWd4NcC", "The Fellowship of the Ring", listOf("John Ronald Reuel Tolkien"),
        "2003-09-05", 436, 0, 0, "Still need to read", false
    )

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsBookToDatabase() = runBlocking {
        bookDao.insert(testBook1)
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], testBook1)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesBookInDatabase() = runBlocking {
        bookDao.insert(testBook3)
        var testBook3Update = testBook3.copy()
        testBook3Update.readPageCount = 10
        bookDao.update(testBook3Update)
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], testBook3Update)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesBookFromDatabase() = runBlocking {
        bookDao.insert(testBook1)
        bookDao.delete(testBook1)
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks, emptyList<BookEntity>())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetBook_returnsBookWithGivenID() = runBlocking {
        bookDao.insert(testBook1)
        val book = bookDao.getBook(testBook1.id)
        assertEquals(book, testBook1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllBooks_returnsAllBooksFromDatabase() = runBlocking {
        bookDao.insert(testBook1)
        bookDao.insert(testBook2)
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], testBook1)
        assertEquals(allBooks[1], testBook2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetUnfinishedBooks_returnsBooksWhereReadPagesIsLessThanTotalPages() = runBlocking {
        bookDao.insert(testBook1)
        bookDao.insert(testBook2)
        bookDao.insert(testBook3)
        val allBooks = bookDao.getAllBooks().first()
        val unfinishedBooks = bookDao.getUnfinishedBooks().first()
        assertNotEquals(allBooks, unfinishedBooks)
        assertEquals(unfinishedBooks[0], testBook3)
    }
}