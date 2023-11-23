package com.example.xt3.domain

data class Tweet(
    val id: TweetId,
    val content: String,
)

@JvmInline
value class TweetId(val value: Long)
