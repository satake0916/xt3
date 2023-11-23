package com.example.xt3.service

import com.example.xt3.domain.Tweet
import com.example.xt3.domain.TweetEntity
import com.example.xt3.domain.TweetId
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class TweetService {

    // read user by user primary key
    fun scanTweet(): Array<Tweet> {
        return TweetEntity.selectAll().map {
            Tweet(
                id = TweetId((it[TweetEntity.id].value)),
                content = it[TweetEntity.content],
            )
        }.toTypedArray()
    }

    // create Tweet
    fun create(request: TweetCreateRequest): TweetId {
        val id = TweetEntity.insertAndGetId {
            it[content] = request.content
        }

        return TweetId(id.value)
    }
}

data class TweetCreateRequest(
    val content: String
)
