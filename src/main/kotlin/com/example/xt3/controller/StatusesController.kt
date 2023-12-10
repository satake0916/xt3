package com.example.xt3.controller

import com.example.xt3.domain.service.TweetService
import com.example.xt3.openapi.generated.controller.StatusesApi
import com.example.xt3.openapi.generated.model.GetTweetsRes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusesController(
    private val tweetService: TweetService
) : StatusesApi {
    private val logger = LoggerFactory.getLogger(StatusesController::class.java)
    override fun v1StatusesAccountTimelineAccountIdGet(
        accountId: String,
        count: Int,
        maxId: String?,
        sinceId: String?
    ): ResponseEntity<GetTweetsRes> {
        return try {
            ResponseEntity(
                tweetService.getAllTweetsByFollowedAccountsByAccountId(
                    accountId = accountId,
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
}
