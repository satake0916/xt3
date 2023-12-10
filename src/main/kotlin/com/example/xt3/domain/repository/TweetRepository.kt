package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.AccountsEntity
import com.example.xt3.domain.entity.TweetsEntity
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.TweetQueryDto
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
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
        val query = TweetsEntity
            .selectAll()
            .limit(count)
            .orderBy(TweetsEntity.createdAt)
        maxId?.let {
            query.andWhere { TweetsEntity.tweetId lessEq it.valueLong }
        }
        sinceId?.let {
            query.andWhere { TweetsEntity.tweetId greaterEq it.valueLong }
        }
        return query
            .map {
                convertResultRowToTweetDto(it)
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
                convertResultRowToTweetDto(it)
            }
    }

    fun selectTweetsByAccountId(
        accountIdList: List<AccountId>,
        count: Int,
        maxId: TweetId?,
        sinceId: TweetId?,
    ): List<TweetDto> {
        val query = TweetsEntity
            .select(TweetsEntity.accountId.inList(accountIdList.map { it.valueLong }))
            .limit(count)
            .orderBy(TweetsEntity.createdAt)
        maxId?.let {
            query.andWhere { TweetsEntity.tweetId lessEq it.valueLong }
        }
        sinceId?.let {
            query.andWhere { TweetsEntity.tweetId greaterEq it.valueLong }
        }
        return query
            .map {
                convertResultRowToTweetDto(it)
            }
    }

    fun convertResultRowToTweetDto(resultRow: ResultRow): TweetDto {
        return TweetDto(
            tweetId = TweetId(resultRow[TweetsEntity.tweetId]),
            accountId = AccountId(resultRow[TweetsEntity.accountId]),
            tweetText = resultRow[TweetsEntity.tweetText],
            parentTweetId = resultRow[TweetsEntity.parentTweetId]?.let { it1 -> TweetId(it1) },
            createdAt = resultRow[TweetsEntity.createdAt],
            updatedAt = resultRow[TweetsEntity.updatedAt]
        )
    }
}
