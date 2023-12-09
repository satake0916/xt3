package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.AccountsEntity
import com.example.xt3.domain.entity.FollowersEntity
import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class AccountRepository {
    fun getAccountsByAccountId(accountId: AccountId): AccountDto? {
        return AccountsEntity.select {
            AccountsEntity.accountId eq accountId.valueLong
        }.firstOrNull()?.let {
            AccountDto(
                accountId = AccountId(it[AccountsEntity.accountId]),
                userId = UserId(it[AccountsEntity.userId]),
                accountName = it[AccountsEntity.displayName],
                displayName = it[AccountsEntity.displayName],
                profileDescription = it[AccountsEntity.profileDescription],
                profileImageUrl = it[AccountsEntity.profileImageUrl],
                isPrimary = it[AccountsEntity.isPrimary],
                createdAt = it[AccountsEntity.createdAt],
                updatedAt = it[AccountsEntity.updatedAt]
            )
        }
    }

    fun getAccountsByUserId(userId: UserId): List<AccountDto> {
        return AccountsEntity.select { AccountsEntity.userId eq userId.valueLong }.map {
            AccountDto(
                accountId = AccountId(it[AccountsEntity.accountId]),
                userId = UserId(it[AccountsEntity.userId]),
                accountName = it[AccountsEntity.accountName],
                displayName = it[AccountsEntity.displayName],
                profileDescription = it[AccountsEntity.profileDescription],
                profileImageUrl = it[AccountsEntity.profileImageUrl],
                isPrimary = it[AccountsEntity.isPrimary],
                createdAt = it[AccountsEntity.createdAt],
                updatedAt = it[AccountsEntity.updatedAt]
            )
        }
    }

    fun getAccountsFollowedByAccountId(accountId: AccountId): List<AccountId> {
        return FollowersEntity.select {
            FollowersEntity.followingAccountId eq accountId.valueLong
        }.map {
            AccountId(it[FollowersEntity.followedAccountId])
        }
    }
}
