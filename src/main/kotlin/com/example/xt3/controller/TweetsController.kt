package com.example.xt3.controller

import com.example.xt3.domain.service.TweetCreateRequest
import com.example.xt3.domain.service.TweetService
import com.example.xt3.openapi.generated.controller.TweetsApi
import com.example.xt3.openapi.generated.model.TweetRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetsController(
    private val tweetService: TweetService
) : TweetsApi {

    override fun tweetsGet(): ResponseEntity<List<TweetRes>> {
        return ResponseEntity(
            tweetService.getAllTweets(),
            HttpStatus.OK
        )
    }
    override fun tweetsPost(tweetRes: TweetRes): ResponseEntity<Unit> {
        tweetService.create(
            TweetCreateRequest(
                accountId = tweetRes.accountId,
                text = tweetRes.text,
            )
        )
        return ResponseEntity(
            HttpStatus.OK
        )
    }

    override fun tweetsTweetIdGet(tweetId: Long): ResponseEntity<TweetRes> {
        return ResponseEntity(
            tweetService.findByTweetId(tweetId),
            HttpStatus.OK
        )
    }
}
