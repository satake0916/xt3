package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.Accounts
import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class AccountRepository {
    fun getAccountsByUserId(userId: UserId): List<AccountDto> {
        return Accounts.select { Accounts.userId eq userId.value }.map {
            AccountDto(
                accountId = AccountId(it[Accounts.accountId]),
                userId = UserId(it[Accounts.userId]),
                accountName = it[Accounts.accountName],
                displayName = it[Accounts.displayName],
                profileDescription = it[Accounts.profileDescription],
                profileImageUrl = it[Accounts.profileImageUrl],
                isPrimary = it[Accounts.isPrimary],
                createdAt = it[Accounts.createdAt],
                updatedAt = it[Accounts.updatedAt]
            )
        }
    }
}
