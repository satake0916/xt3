package com.example.xt3.controller

import com.example.xt3.domain.service.AccountService
import com.example.xt3.openapi.generated.controller.AccountsApi
import com.example.xt3.openapi.generated.model.AccountRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountsController(
    private val accountService: AccountService
) : AccountsApi {
    override fun accountsAccountIdGet(accountId: Long): ResponseEntity<AccountRes> {
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
}
