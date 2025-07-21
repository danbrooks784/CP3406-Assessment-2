package com.example.cp3406assessment2.data

import com.example.cp3406assessment2.data.api.BookAPI
import com.example.cp3406assessment2.data.api.NetworkResult
import com.example.cp3406assessment2.data.database.BookDao
import com.example.cp3406assessment2.data.database.BookEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.toString

class BookRepositoryImpl(
    private val bookAPI: BookAPI,
    private val dispatcher: CoroutineDispatcher,
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun searchByQuery(query: String): NetworkResult<List<Book>> {
        return withContext(dispatcher) {
            try {
                val response = bookAPI.searchBooks(query = query, maxResults = 20)
                if (response.isSuccessful) {
                    var searchResult = mutableListOf<Book>()
                    for (item in response.body()!!.items) {
                        searchResult.add(item.volumeInfo)
                    }

                    NetworkResult.Success(searchResult)
                } else {
                    NetworkResult.Error(
                        response.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "API Error")
            }
        }
    }

    override suspend fun getBooksFromDatabase(): Flow<List<Book>> {
        return withContext(dispatcher) {
            bookDao.getAllBooks().map { books ->
                books.map { bookEntity ->
                    convertBookEntityToBook(bookEntity)
                }
            }
        }
    }

    override suspend fun getFavouriteBooks(): Flow<List<Book>> {
        return withContext(dispatcher) {
            bookDao.getFavouriteBooks().map { books ->
                books.map { bookEntity ->
                    convertBookEntityToBook(bookEntity)
                }
            }
        }
    }

    override suspend fun insertBook(book: Book) {
        withContext(dispatcher) {
            bookDao.insert(convertBookToBookEntity(book))
        }
    }

    override suspend fun deleteBook(book: Book) {
        withContext(dispatcher) {
            bookDao.delete(convertBookToBookEntity(book))
        }
    }

    override suspend fun updateBook(book: Book) {
        withContext(dispatcher) {
            bookDao.update(convertBookToBookEntity(book))
        }
    }

    override fun convertBookToBookEntity(book: Book): BookEntity {
        return BookEntity(
            id = book.id,
            title = book.title,
            authors = book.authors,
            year = book.year,
            totalPageCount = book.totalPageCount,
            readPageCount = book.readPageCount,
            rating = book.rating,
            review = book.review,
            isFavourite = book.isFavourite
        )
    }

    override fun convertBookEntityToBook(bookEntity: BookEntity): Book {
        return Book(
            id = bookEntity.id,
            title = bookEntity.title,
            authors = bookEntity.authors,
            year = bookEntity.year,
            totalPageCount = bookEntity.totalPageCount,
            readPageCount = bookEntity.readPageCount,
            rating = bookEntity.rating,
            review = bookEntity.review,
            isFavourite = bookEntity.isFavourite
        )
    }
}
