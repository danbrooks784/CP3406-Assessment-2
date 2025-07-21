package com.example.cp3406assessment2.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val authors: List<String>,
    val year: String,
    val totalPageCount: Int,
    var readPageCount: Int,
    var rating: Int,
    var review: String,
    var isFavourite: Boolean
)