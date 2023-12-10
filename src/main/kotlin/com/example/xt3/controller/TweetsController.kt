package com.example.xt3.controller

import com.example.xt3.domain.service.TweetService
import com.example.xt3.openapi.generated.controller.TweetsApi
import com.example.xt3.openapi.generated.model.GetTweetsByTweetIdRes
import com.example.xt3.openapi.generated.model.GetTweetsRes
import com.example.xt3.openapi.generated.model.TweetReq
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetsController(
    private val tweetService: TweetService,
) : TweetsApi {
    private val logger = LoggerFactory.getLogger(TweetsController::class.java)
    override fun v1TweetsGet(
        count: Int,
        maxId: String?,
        sinceId: String?
    ): ResponseEntity<GetTweetsRes> {
        return try {
            ResponseEntity(
                tweetService.getAllTweets(
                    count = count,
                    maxId = maxId,
                    sinceId = sinceId,
                ),
                HttpStatus.OK
            )
        } catch (e: IllegalArgumentException) {
            logger.info(e.toString())
            ResponseEntity(
                HttpStatus.BAD_REQUEST
            )
        }
    }

    override fun v1TweetsTweetIdGet(tweetId: String): ResponseEntity<GetTweetsByTweetIdRes> {
        return super.v1TweetsTweetIdGet(tweetId)
    }

    override fun v1TweetsPost(tweetReq: TweetReq): ResponseEntity<Unit> {
        tweetService.postTweet(tweetReq)
        return ResponseEntity(HttpStatus.OK)
    }
}
