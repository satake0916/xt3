package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.TweetQueryDto
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.domain.repository.TweetRepository
import com.example.xt3.openapi.generated.model.GetTweetsRes
import com.example.xt3.openapi.generated.model.TweetReq
import com.example.xt3.openapi.generated.model.TweetRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TweetService(
    private val tweetRepository: TweetRepository,
    private val accountRepository: AccountRepository,
) {
    fun postTweet(tweetReq: TweetReq) {
        tweetRepository.insertTweet(
            TweetQueryDto(
                accountId = AccountId(tweetReq.accountId),
                tweetText = tweetReq.tweetText
            )
        )
    }

    @Throws(IllegalArgumentException::class)
    fun getAllTweets(count: Int, maxId: String?, sinceId: String?): GetTweetsRes {
        val maxTweetId = maxId?.let { TweetId(it) }
        val sinceTweetId = sinceId?.let { TweetId(it) }

        if (maxTweetId != null && sinceTweetId != null) {
            require(maxTweetId >= sinceTweetId)
        }

        val tweets = tweetRepository.selectAllTweet(
            count = count,
            maxId = maxTweetId,
            sinceId = sinceTweetId,
        ).map {
            convertTweetDtoToTweetRes(it)
        }
        return GetTweetsRes(
            total = tweets.size,
            tweets = tweets
        )
    }

    @Throws(IllegalArgumentException::class)
    fun getAllTweetsByFollowedAccountsByAccountId(
        accountId: String,
        count: Int,
        maxId: String?,
        sinceId: String?
    ): GetTweetsRes {
        val maxTweetId = maxId?.let { TweetId(it) }
        val sinceTweetId = sinceId?.let { TweetId(it) }

        if (maxTweetId != null && sinceTweetId != null) {
            require(maxTweetId >= sinceTweetId)
        }

        // REVIEW: accountIdがログイン中のuserIdのものなのかチェックが必要
        val followingAccounts = accountRepository
            .selectAccountsFollowedByAccountId(AccountId(accountId))
        val tweets = tweetRepository
            .selectTweetsByAccountId(
                accountIdList = followingAccounts.plus(AccountId(accountId)),
                count = count,
                maxId = maxTweetId,
                sinceId = sinceTweetId,
            )
            .map { convertTweetDtoToTweetRes(it) }

        return GetTweetsRes(
            total = tweets.size,
            tweets = tweets
        )
    }

    @Throws(IllegalArgumentException::class)
    fun getTweetsByAccountId(
        accountId: String,
        count: Int,
        maxId: String?,
        sinceId: String?
    ): GetTweetsRes {
        val maxTweetId = maxId?.let { TweetId(it) }
        val sinceTweetId = sinceId?.let { TweetId(it) }

        if (maxTweetId != null && sinceTweetId != null) {
            require(maxTweetId >= sinceTweetId)
        }

        val tweets = tweetRepository.selectTweetsByAccountId(
            accountIdList = listOf(AccountId(accountId)),
            count = count,
            maxId = maxTweetId,
            sinceId = sinceTweetId,
        ).map {
            convertTweetDtoToTweetRes(it)
        }
        return GetTweetsRes(
            total = tweets.size,
            tweets = tweets
        )
    }

    fun convertTweetDtoToTweetRes(
        tweetDto: TweetDto
    ): TweetRes {
        val accountDto = accountRepository.getAccountsByAccountId(tweetDto.accountId)!!
        return TweetRes(
            tweetId = tweetDto.tweetId.getValueStr(),
            accountId = tweetDto.accountId.getValueStr(),
            accountName = accountDto.accountName,
            displayName = accountDto.displayName,
            tweetText = tweetDto.tweetText,
            createdAt = tweetDto.createdAt
        )
    }
}
