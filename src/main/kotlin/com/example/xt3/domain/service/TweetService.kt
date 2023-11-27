package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.TweetQueryDto
import com.example.xt3.domain.repository.TweetRepository
import com.example.xt3.openapi.generated.model.TweetReq
import com.example.xt3.openapi.generated.model.TweetRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TweetService(
    private val tweetRepository: TweetRepository
) {
    fun postTweet(tweetReq: TweetReq) {
        tweetRepository.insertTweet(
            TweetQueryDto(
                accountId = AccountId(tweetReq.accountId),
                text = tweetReq.text
            )
        )
    }

    fun getAllTweets(): List<TweetRes> {
        return tweetRepository.selectAllTweet().map {
            TweetRes(
                id = it.id.value,
                accountId = it.accountId.value,
                accountName = it.accountName,
                text = it.text,
                createdAt = it.createdAt
            )
        }
    }

    fun findByTweetId(tweetId: Long): TweetRes? {
        return tweetRepository.selectTweetById(TweetId(tweetId))?.let {
            TweetRes(
                id = it.id.value,
                accountId = it.accountId.value,
                accountName = it.accountName,
                text = it.text,
                createdAt = it.createdAt
            )
        }
    }
}
