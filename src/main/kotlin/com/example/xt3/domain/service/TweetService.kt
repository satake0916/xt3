package com.example.xt3.domain.service

import com.example.xt3.domain.entity.Tweets
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.openapi.generated.model.TweetRes
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class TweetService {

    // read user by user primary key
    fun scanTweet(): Array<TweetDto> {
        return Tweets.selectAll().map {
            TweetDto(
                id = TweetId((it[Tweets.id].value)),
                text = it[Tweets.text],
            )
        }.toTypedArray()
    }

    // create Tweet
    fun create(request: TweetCreateRequest): TweetId {
        val id = Tweets.insertAndGetId {
            it[accountId] = request.accountId
            it[text] = request.text
        }

        return TweetId(id.value)
    }

    fun getAllTweets(): List<TweetRes> {
        return Tweets.selectAll().map {
            TweetRes(
                accountId = it[Tweets.accountId],
                text = it[Tweets.text],
            )
        }.toList()
    }

    fun findByTweetId(tweetId: Long): TweetRes? {
        return Tweets.select(Tweets.id eq tweetId).firstOrNull()?.let {
            TweetRes(
                accountId = it[Tweets.accountId],
                text = it[Tweets.text]
            )
        }
    }
}

data class TweetCreateRequest(
    val accountId: Long,
    val text: String
)
