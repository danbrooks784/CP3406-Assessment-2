package com.example.cp3406assessment2.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse(
    @SerialName("items")
    val items: List<APIResponseItems>
)