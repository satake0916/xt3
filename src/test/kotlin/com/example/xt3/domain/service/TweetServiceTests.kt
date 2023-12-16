package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.TweetDto
import com.example.xt3.domain.model.dto.TweetId
import com.example.xt3.domain.model.dto.UserId
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.domain.repository.TweetRepository
import com.example.xt3.openapi.generated.model.TweetRes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

class TweetServiceTests : FunSpec({
    lateinit var mockTweetRepository: TweetRepository
    lateinit var mockAccountRepository: AccountRepository
    lateinit var tweetService: TweetService

    beforeTest {
        mockTweetRepository = mockk()
        mockAccountRepository = mockk()
        tweetService = TweetService(mockTweetRepository, mockAccountRepository)
    }

    context("convertTweetDtoToTweetRes") {
        test("normal") {
            val sampleTweetDto = TweetDto(
                tweetId = TweetId(1),
                tweetText = "first tweet",
                accountId = AccountId(1),
                accountName = "one_first",
                displayName = "First",
                parentTweetId = null,
                createdAt = LocalDateTime.of(2023, 12, 10, 11, 11),
                updatedAt = LocalDateTime.of(2023, 12, 10, 11, 11)
            )

            val actualTweetRes = tweetService.convertTweetDtoToTweetRes(sampleTweetDto)
            val expectedTweetRes = TweetRes(
                tweetId = "1",
                tweetText = "first tweet",
                accountId = "1",
                accountName = "one_first",
                displayName = "First",
                createdAt = LocalDateTime.of(2023, 12, 10, 11, 11),
            )

            actualTweetRes shouldBe expectedTweetRes
        }
    }

    context("getAllTweets") {
        test("non-normal") {
            shouldThrow<IllegalArgumentException> {
                tweetService.getAllTweets(
                    count = 30,
                    maxId = "5",
                    sinceId = "10",
                )
            }
        }
    }

    context("getTweetsByAccountId") {
        test("non-normal") {
            shouldThrow<IllegalArgumentException> {
                tweetService.getTweetsByAccountId(
                    accountId = "1",
                    count = 30,
                    maxId = "5",
                    sinceId = "10",
                )
            }
        }
    }

    context("getAllTweetsByFollowedAccountsByAccountId") {
        test("non-normal") {
            shouldThrow<IllegalArgumentException> {
                tweetService.getAllTweetsByFollowedAccountsByAccountId(
                    accountId = "1",
                    count = 30,
                    maxId = "5",
                    sinceId = "10",
                )
            }
        }
    }
})
