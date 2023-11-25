package com.example.xt3.domain.model.dto

data class TweetDto(
    val id: TweetId,
    val text: String,
)

@JvmInline
value class TweetId(val value: Long)
