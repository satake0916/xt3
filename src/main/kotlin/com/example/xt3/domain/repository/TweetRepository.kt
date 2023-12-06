package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.Accounts
import com.example.xt3.domain.entity.Tweets
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.TweetQueryDto
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class TweetRepository {
    fun insertTweet(tweetQueryDto: TweetQueryDto) {
        Tweets.insert {
            it[accountId] = tweetQueryDto.accountId.value
            it[tweetText] = tweetQueryDto.tweetText
        }
    }

    fun selectAllTweet(): List<TweetDto> {
        return Tweets
            .selectAll()
            .map {
                TweetDto(
                    tweetId = TweetId(it[Tweets.tweetId]),
                    accountId = AccountId(it[Tweets.accountId]),
                    tweetText = it[Tweets.tweetText],
                    parentTweetId = it[Tweets.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[Tweets.createdAt],
                    updatedAt = it[Tweets.updatedAt]
                )
            }
    }

    fun selectTweetById(tweetId: TweetId): TweetDto? {
        return Tweets
            .join(
                otherTable = Accounts,
                JoinType.INNER,
                onColumn = Tweets.accountId,
                otherColumn = Accounts.accountId
            ).select(Tweets.tweetId eq tweetId.value).firstOrNull()?.let {
                TweetDto(
                    tweetId = TweetId(it[Tweets.tweetId]),
                    accountId = AccountId(it[Tweets.accountId]),
                    tweetText = it[Tweets.tweetText],
                    parentTweetId = it[Tweets.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[Tweets.createdAt],
                    updatedAt = it[Tweets.updatedAt]
                )
            }
    }

    fun selectTweetByAccountId(accountId: AccountId): List<TweetDto> {
        return Tweets
            .select(Tweets.accountId eq accountId.value)
            .orderBy(Tweets.createdAt)
            .map {
                TweetDto(
                    tweetId = TweetId(it[Tweets.tweetId]),
                    accountId = AccountId(it[Tweets.accountId]),
                    tweetText = it[Tweets.tweetText],
                    parentTweetId = it[Tweets.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[Tweets.createdAt],
                    updatedAt = it[Tweets.updatedAt]
                )
            }
    }

    fun selectTweetByMultiAccountId(accountIdList: List<AccountId>): List<TweetDto> {
        return Tweets
            .select(Tweets.accountId.inList(accountIdList.map { it.value }))
            .orderBy(Tweets.createdAt)
            .map {
                TweetDto(
                    tweetId = TweetId(it[Tweets.tweetId]),
                    accountId = AccountId(it[Tweets.accountId]),
                    tweetText = it[Tweets.tweetText],
                    parentTweetId = it[Tweets.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[Tweets.createdAt],
                    updatedAt = it[Tweets.updatedAt]
                )
            }
    }
}
