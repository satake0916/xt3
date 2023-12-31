package com.example.xt3.controller

import com.example.xt3.domain.service.AccountService
import com.example.xt3.domain.service.TweetService
import com.example.xt3.openapi.generated.controller.AccountsApi
import com.example.xt3.openapi.generated.model.AccountRes
import com.example.xt3.openapi.generated.model.FollowingReq
import com.example.xt3.openapi.generated.model.FollowingRes
import com.example.xt3.openapi.generated.model.GetAccountsRes
import com.example.xt3.openapi.generated.model.GetTweetsRes
import com.example.xt3.openapi.generated.model.UnfollowRes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountsController(
    private val accountService: AccountService,
    private val tweetService: TweetService,
) : AccountsApi {
    private val logger = LoggerFactory.getLogger(AccountsController::class.java)
    override fun v1AccountsAccountIdGet(accountId: String): ResponseEntity<AccountRes> {
        val account = accountService.findByAccountId(accountId)
        return if (account == null) {
            ResponseEntity(
                HttpStatus.NOT_FOUND
            )
        } else {
            ResponseEntity(
                account,
                HttpStatus.OK
            )
        }
    }

    override fun v1AccountsByAccountNameAccountNameGet(
        accountName: String
    ): ResponseEntity<AccountRes> {
        val account = accountService.findByAccountName(accountName)
        return if (account == null) {
            ResponseEntity(
                HttpStatus.NOT_FOUND
            )
        } else {
            ResponseEntity.ok(
                account,
            )
        }
    }

    override fun v1AccountsAccountIdTweetsGet(
        accountId: String,
        count: Int,
        maxId: String?,
        sinceId: String?
    ): ResponseEntity<GetTweetsRes> {
        return try {
            ResponseEntity(
                tweetService.getTweetsByAccountId(
                    accountId = accountId,
                    count = count,
                    maxId = maxId,
                    sinceId = sinceId
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

    override fun v1AccountsAccountIdFollowingGet(
        accountId: String,
        limit: Int,
        offset: Long?
    ): ResponseEntity<GetAccountsRes> {
        return ResponseEntity(
            accountService.findFollowingAccountsByAccountId(
                accountId = accountId,
                limit = limit,
                offset = offset,
            ),
            HttpStatus.OK
        )
    }

    override fun v1AccountsAccountIdFollowingPost(
        accountId: String,
        followingReq: FollowingReq,
    ): ResponseEntity<FollowingRes> {
        accountService.followFollowedIdAccountByFollowingAccountId(
            followingAccountId = accountId,
            followedAccountId = followingReq.followedId
        )
        return ResponseEntity.ok(
            FollowingRes(
                following = true
            )
        )
    }

    override fun v1AccountsSourceAccountIdFollowingTargetAccountIdDelete(
        sourceAccountId: String,
        targetAccountId: String,
    ): ResponseEntity<UnfollowRes> {
        return ResponseEntity.ok(
            accountService.sourceAccountIdUnfollowTargetAccountId(
                sourceAccountId = sourceAccountId,
                targetAccountId = targetAccountId,
            )
        )
    }
}
