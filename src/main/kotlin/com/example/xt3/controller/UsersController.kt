package com.example.xt3.controller

import com.example.xt3.domain.service.UserService
import com.example.xt3.openapi.generated.controller.NotFoundException
import com.example.xt3.openapi.generated.controller.UsersApi
import com.example.xt3.openapi.generated.model.UserWithAccountsRes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val userService: UserService
) : UsersApi {
    private val logger = LoggerFactory.getLogger(UsersController::class.java)
    override fun v1UsersUserIdGet(userId: String): ResponseEntity<UserWithAccountsRes> {
        return try {
            ResponseEntity(
                userService.getUserAndAccountsByUserId(userId),
                HttpStatus.OK
            )
        } catch (e: NotFoundException) {
            // REVIEW: ロギングを考える
            logger.info(e.toString())
            ResponseEntity(
                HttpStatus.BAD_REQUEST
            )
        }
    }
}
