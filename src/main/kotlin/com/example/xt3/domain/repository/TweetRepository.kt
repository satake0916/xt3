package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.AccountsEntity
import com.example.xt3.domain.entity.TweetsEntity
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
        TweetsEntity.insert {
            it[accountId] = tweetQueryDto.accountId.valueLong
            it[tweetText] = tweetQueryDto.tweetText
        }
    }

    fun selectAllTweet(): List<TweetDto> {
        return TweetsEntity
            .selectAll()
            .map {
                TweetDto(
                    tweetId = TweetId(it[TweetsEntity.tweetId]),
                    accountId = AccountId(it[TweetsEntity.accountId]),
                    tweetText = it[TweetsEntity.tweetText],
                    parentTweetId = it[TweetsEntity.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[TweetsEntity.createdAt],
                    updatedAt = it[TweetsEntity.updatedAt]
                )
            }
    }

    fun selectTweetById(tweetId: TweetId): TweetDto? {
        return TweetsEntity
            .join(
                otherTable = AccountsEntity,
                JoinType.INNER,
                onColumn = TweetsEntity.accountId,
                otherColumn = AccountsEntity.accountId
            ).select(TweetsEntity.tweetId eq tweetId.valueLong).firstOrNull()?.let {
                TweetDto(
                    tweetId = TweetId(it[TweetsEntity.tweetId]),
                    accountId = AccountId(it[TweetsEntity.accountId]),
                    tweetText = it[TweetsEntity.tweetText],
                    parentTweetId = it[TweetsEntity.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[TweetsEntity.createdAt],
                    updatedAt = it[TweetsEntity.updatedAt]
                )
            }
    }

    fun selectTweetByAccountId(accountId: AccountId): List<TweetDto> {
        return TweetsEntity
            .select(TweetsEntity.accountId eq accountId.valueLong)
            .orderBy(TweetsEntity.createdAt)
            .map {
                TweetDto(
                    tweetId = TweetId(it[TweetsEntity.tweetId]),
                    accountId = AccountId(it[TweetsEntity.accountId]),
                    tweetText = it[TweetsEntity.tweetText],
                    parentTweetId = it[TweetsEntity.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[TweetsEntity.createdAt],
                    updatedAt = it[TweetsEntity.updatedAt]
                )
            }
    }

    fun selectTweetByMultiAccountId(accountIdList: List<AccountId>): List<TweetDto> {
        return TweetsEntity
            .select(TweetsEntity.accountId.inList(accountIdList.map { it.valueLong }))
            .orderBy(TweetsEntity.createdAt)
            .map {
                TweetDto(
                    tweetId = TweetId(it[TweetsEntity.tweetId]),
                    accountId = AccountId(it[TweetsEntity.accountId]),
                    tweetText = it[TweetsEntity.tweetText],
                    parentTweetId = it[TweetsEntity.parentTweetId]?.let { it1 -> TweetId(it1) },
                    createdAt = it[TweetsEntity.createdAt],
                    updatedAt = it[TweetsEntity.updatedAt]
                )
            }
    }
}
