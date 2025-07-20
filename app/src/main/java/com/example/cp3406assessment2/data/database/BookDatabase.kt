package com.example.cp3406assessment2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cp3406assessment2.data.database.BookDao

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)

abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}