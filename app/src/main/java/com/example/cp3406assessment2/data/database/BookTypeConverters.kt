package com.example.cp3406assessment2.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class BookTypeConverters {
    @TypeConverter
    fun convertAuthorsToString(authors: List<String>): String {
        return Json.encodeToString(authors)
    }

    @TypeConverter
    fun convertStringToAuthors(authors: String): List<String> {
        return Json.decodeFromString(authors)
    }
}