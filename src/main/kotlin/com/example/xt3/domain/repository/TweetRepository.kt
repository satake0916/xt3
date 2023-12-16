package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.AccountsEntity
import com.example.xt3.domain.entity.TweetsEntity
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.TweetQueryDto
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.alias
import org.jetbrains.exposed.sql.andWhere
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

    fun selectAllTweet(
        count: Int,
        maxId: TweetId?,
        sinceId: TweetId?,
    ): List<TweetDto> {
        return getTweetDto(
            op = Op.TRUE,
            count = count,
            maxId = maxId,
            sinceId = sinceId,
        )
    }

    fun selectTweetById(tweetId: TweetId): TweetDto? {
        return getTweetDto(
            op = TweetsEntity.tweetId eq tweetId.valueLong,
            count = 1,
            maxId = null,
            sinceId = null,
        ).firstOrNull()
    }

    fun selectTweetsByAccountId(
        accountIdList: List<AccountId>,
        count: Int,
        maxId: TweetId?,
        sinceId: TweetId?,
    ): List<TweetDto> {
        return getTweetDto(
            TweetsEntity.accountId.inList(accountIdList.map { it.valueLong }),
            count,
            maxId,
            sinceId,
        )
    }

    fun getTweetDto(
        op: Op<Boolean>,
        count: Int,
        maxId: TweetId?,
        sinceId: TweetId?
    ): List<TweetDto> {
        val subQuery = TweetsEntity.select { op }
            .limit(count)
            .orderBy(TweetsEntity.createdAt)
            .andWhere { TweetsEntity.tweetId lessEq (maxId?.valueLong ?: Long.MAX_VALUE) }
            .andWhere { TweetsEntity.tweetId greaterEq (sinceId?.valueLong ?: 1L) }
            .alias("subQuery")

        return AccountsEntity.join(
            subQuery,
            JoinType.INNER,
            additionalConstraint = {
                AccountsEntity.accountId eq subQuery[TweetsEntity.accountId]
            }
        ).selectAll().map {
            TweetDto(
                tweetId = TweetId(it[subQuery[TweetsEntity.tweetId]]),
                accountId = AccountId(it[AccountsEntity.accountId]),
                accountName = it[AccountsEntity.accountName],
                displayName = it[AccountsEntity.displayName],
                tweetText = it[subQuery[TweetsEntity.tweetText]],
                parentTweetId = it[subQuery[TweetsEntity.parentTweetId]]
                    ?.let { it1 -> TweetId(it1) },
                createdAt = it[subQuery[TweetsEntity.createdAt]],
                updatedAt = it[subQuery[TweetsEntity.updatedAt]],
            )
        }
    }
}
