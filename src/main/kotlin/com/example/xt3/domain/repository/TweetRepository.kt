package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.Accounts
import com.example.xt3.domain.entity.Tweets
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.TweetQueryDto
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class TweetRepository {
    fun insertTweet(tweetQueryDto: TweetQueryDto) {
        Tweets.insert {
            it[accountId] = tweetQueryDto.accountId.value
            it[text] = tweetQueryDto.text
        }
    }

    fun selectAllTweet(): List<TweetDto> {
        return Tweets
            .join(otherTable = Accounts, JoinType.INNER, onColumn = Tweets.accountId, otherColumn = Accounts.id)
            .selectAll()
            .map {
                TweetDto(
                    id = TweetId(it[Tweets.id].value),
                    accountId = AccountId(it[Tweets.accountId]),
                    accountName = it[Accounts.displayName],
                    text = it[Tweets.text],
                    createdAt = it[Tweets.createdAt]
                )
            }
    }

    fun selectTweetById(tweetId: TweetId): TweetDto? {
        return Tweets
            .join(otherTable = Accounts, JoinType.INNER, onColumn = Tweets.accountId, otherColumn = Accounts.id)
            .select(Tweets.id eq tweetId.value).firstOrNull()?.let {
                TweetDto(
                    id = TweetId(it[Tweets.id].value),
                    accountId = AccountId(it[Tweets.accountId]),
                    accountName = it[Accounts.displayName],
                    text = it[Tweets.text],
                    createdAt = it[Tweets.createdAt]
                )
            }
    }
}
