package com.example.xt3.domain.service

import com.example.xt3.domain.entity.Accounts
import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService {
    fun findByAccountId(accountId: Long): AccountDto? {
        val account = Accounts.select { Accounts.id eq accountId }.firstOrNull()?.let {
            AccountDto(
                id = AccountId(it[Accounts.id].value),
                userId = UserId(it[Accounts.userId]),
                displayName = it[Accounts.displayName],
                description = it[Accounts.description],
            )
        }
        return account
    }
}
