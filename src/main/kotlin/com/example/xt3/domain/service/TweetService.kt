package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetQueryDto
import com.example.xt3.domain.repository.TweetRepository
import com.example.xt3.openapi.generated.model.GetTweetsRes
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
                tweetText = tweetReq.tweetText
            )
        )
    }

    fun getAllTweets(): GetTweetsRes {
        val tweets = tweetRepository.selectAllTweet().map {
            TweetRes(
                tweetId = it.tweetId.value,
                accountId = it.accountId.value,
                tweetText = it.tweetText,
                createdAt = it.createdAt
            )
        }
        return GetTweetsRes(
            total = tweets.size,
            tweets = tweets
        )
    }

    /*
    fun findByTweetId(tweetId: Long): GetTweetsByTweetIdRes {
        return GetTweetsByTweetIdRes(
            data = TweetRes(),
            include = emptyList()
        )
    }
     */

    fun getAllTweetsByAccountId(
        accountId: Long,
    ): GetTweetsRes {
        val tweets = tweetRepository.selectTweetByAccountId(AccountId(accountId)).map {
            TweetRes(
                tweetId = it.tweetId.value,
                accountId = it.accountId.value,
                tweetText = it.tweetText,
                createdAt = it.createdAt
            )
        }
        return GetTweetsRes(
            total = tweets.size,
            tweets = tweets
        )
    }
}
