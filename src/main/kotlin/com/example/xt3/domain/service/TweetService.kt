package com.example.xt3.domain.service

import com.example.xt3.domain.entity.Tweets
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import org.jetbrains.exposed.sql.insertAndGetId
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
            it[text] = request.text
        }

        return TweetId(id.value)
    }
}

data class TweetCreateRequest(
    val text: String
)
