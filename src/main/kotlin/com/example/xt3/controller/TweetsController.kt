package com.example.xt3.controller

import com.example.xt3.domain.service.TweetService
import com.example.xt3.openapi.generated.controller.TweetsApi
import com.example.xt3.openapi.generated.model.GetTweetsRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetsController(
    private val tweetService: TweetService
) : TweetsApi {

    override fun tweetsGet(): ResponseEntity<GetTweetsRes> {
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

    override fun tweetsByAccountIdAccountIdGet(accountId: Long): ResponseEntity<GetTweetsRes> {
        return ResponseEntity(
            tweetService.getAllTweetsByAccountId(accountId),
            HttpStatus.OK
        )
    }
}
