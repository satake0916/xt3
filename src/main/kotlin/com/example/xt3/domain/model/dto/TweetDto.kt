package com.example.xt3.domain.model.dto

import java.time.LocalDateTime

data class TweetDto(
    val tweetId: TweetId,
    val accountId: AccountId,
    val tweetText: String,
    val parentTweetId: TweetId?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

@JvmInline
value class TweetId(val value: Long)
