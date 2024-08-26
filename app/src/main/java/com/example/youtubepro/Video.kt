package com.example.youtubepro

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val likes: Int,
    val channelName: String,
    val videoId: String,
)