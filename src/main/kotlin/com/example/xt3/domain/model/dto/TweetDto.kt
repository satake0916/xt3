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

data class TweetId(val valueLong: Long) : Comparable<TweetId> {
    constructor(valueStr: String) : this(valueStr.toLong())

    fun getValueStr(): String {
        return valueLong.toString()
    }

    override fun compareTo(other: TweetId): Int {
        val diff = valueLong - other.valueLong
        if (diff > Int.MAX_VALUE) return 1
        if (diff < Int.MIN_VALUE) return -1
        return diff.toInt()
    }
}
