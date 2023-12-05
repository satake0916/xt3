package com.example.xt3.controller

import com.example.xt3.domain.service.UserService
import com.example.xt3.openapi.generated.controller.NotFoundException
import com.example.xt3.openapi.generated.controller.UsersApi
import com.example.xt3.openapi.generated.model.UserWithAccountsRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val userService: UserService
) : UsersApi {
    override fun usersUserIdGet(userId: Long): ResponseEntity<UserWithAccountsRes> {
        return try {
            ResponseEntity(
                userService.getUserAndAccountsByUserId(userId),
                HttpStatus.OK
            )
        } catch (e: NotFoundException) {
            // REVIEW: ロギングを考える
            println(e)
            ResponseEntity(
                HttpStatus.BAD_REQUEST
            )
        }
    }
}
