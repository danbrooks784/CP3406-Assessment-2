package com.example.cp3406assessment2.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("title")
    val title: String = "Title",

    @SerialName("authors")
    val authors: List<String> = emptyList<String>(),

    @SerialName("publishedDate")
    val year: String = "",

    @SerialName("pageCount")
    val totalPageCount: Int = 0,

    var readPageCount: Int = 0,
    var rating: Int = 0,
    var review: String = ""
)