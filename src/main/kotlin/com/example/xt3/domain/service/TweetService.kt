package com.example.xt3.domain.service

import com.example.xt3.domain.entity.Tweets
import com.example.xt3.openapi.generated.model.TweetRes
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TweetService {
    fun postTweet(tweetRes: TweetRes) {
        Tweets.insert {
            it[accountId] = tweetRes.accountId
            it[text] = tweetRes.text
        }
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
