package com.example.signlanghub.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("keyword")
    val keyword: String,
    @SerialName("video_url")
    val videoUrl: String,
)
