package com.example.xt3.domain.model.dto

import java.time.LocalDateTime

data class TweetDto(
    val id: TweetId,
    val accountId: AccountId,
    val accountName: String,
    val text: String,
    val createdAt: LocalDateTime
)

@JvmInline
value class TweetId(val value: Long)
