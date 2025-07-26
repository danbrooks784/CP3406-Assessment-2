package com.example.cp3406assessment2.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookEntity: BookEntity)

    @Update
    suspend fun update(bookEntity: BookEntity)

    @Delete
    suspend fun delete(bookEntity: BookEntity)

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBook(id: String): BookEntity

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isFavourite = 1")
    fun getFavouriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE readPageCount = 0")
    fun getUnreadBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE readPageCount < totalPageCount")
    fun getUnfinishedBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books as a WHERE rating = (SELECT MAX(rating) FROM books as b WHERE a.id = b.id)")
    fun getHighestRatedBooks(): Flow<List<BookEntity>>
}