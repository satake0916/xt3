package com.example.xt3.controller

import com.example.xt3.domain.service.TweetService
import com.example.xt3.openapi.generated.controller.TweetsApi
import com.example.xt3.openapi.generated.model.GetTweetsRes
import com.example.xt3.openapi.generated.model.TweetReq
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetsController(
    private val tweetService: TweetService
) : TweetsApi {

    override fun v1TweetsGet(): ResponseEntity<GetTweetsRes> {
        return ResponseEntity(
            tweetService.getAllTweets(),
            HttpStatus.OK
        )
    }

    /*
    override fun tweetsTweetIdGet(tweetId: Long): ResponseEntity<GetTweetsByTweetIdRes> {
        return ResponseEntity(
            tweetService.findByTweetId(tweetId),
            HttpStatus.OK
        )
    }
     */

    override fun v1TweetsByAccountIdAccountIdGet(accountId: String): ResponseEntity<GetTweetsRes> {
        return ResponseEntity(
            tweetService.getAllTweetsByAccountId(accountId),
            HttpStatus.OK
        )
    }

    override fun v1TweetsByFolloweeAccountIdGet(accountId: String): ResponseEntity<GetTweetsRes> {
        return ResponseEntity(
            tweetService.getAllTweetsByFollowedAccountsByAccountId(accountId),
            HttpStatus.OK
        )
    }

    override fun v1TweetsPost(tweetReq: TweetReq): ResponseEntity<Unit> {
        tweetService.postTweet(tweetReq)
        return ResponseEntity(HttpStatus.OK)
    }
}
