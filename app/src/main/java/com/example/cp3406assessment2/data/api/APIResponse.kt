package com.example.cp3406assessment2.data.api

import com.example.cp3406assessment2.data.api.APIResponseItems
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse(
    @SerialName("items")
    val items: List<APIResponseItems>
)