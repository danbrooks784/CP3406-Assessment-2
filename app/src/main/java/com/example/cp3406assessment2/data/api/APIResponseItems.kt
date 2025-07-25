package com.example.cp3406assessment2.data.api

import com.example.cp3406assessment2.data.Book
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponseItems(
    @SerialName("id")
    val id: String,

    @SerialName("volumeInfo")
    val volumeInfo: Book
)