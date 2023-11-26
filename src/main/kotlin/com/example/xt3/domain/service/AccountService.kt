package com.example.xt3.domain.service

import com.example.xt3.domain.entity.Accounts
import com.example.xt3.openapi.generated.model.AccountRes
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService {
    fun findByAccountId(accountId: Long): AccountRes? {
        return Accounts.select { Accounts.id eq accountId }.firstOrNull()?.let {
            AccountRes(
                id = it[Accounts.id].value,
                accountName = it[Accounts.displayName],
                description = it[Accounts.description],
            )
        }
    }
}
