package com.example.xt3.controller

import com.example.xt3.domain.model.dto.UserDto
import com.example.xt3.domain.service.AccountService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class AccountController(
    private val accountService: AccountService
) {
    @GetMapping("/accounts/{accountId}/profile")
    fun profile(
        model: Model,
        @AuthenticationPrincipal expectedUserDto: UserDto,
        @PathVariable accountId: Long
    ): String {
        val accountDto = accountService.findByAccountId(accountId = accountId) ?: return "noAccount"
        model["displayName"] = accountDto.displayName
        model["description"] = accountDto.description
        return "accountProfile"
    }
}
