package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.AccountsEntity
import com.example.xt3.domain.entity.FollowersEntity
import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.alias
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class AccountRepository {
    fun getAccountsByAccountId(accountId: AccountId): AccountDto? {
        return AccountsEntity.select {
            AccountsEntity.accountId eq accountId.valueLong
        }.firstOrNull()?.let {
            convertAccountsRowToAccountDto(it)
        }
    }

    fun getAccountByAccountName(accountName: String): AccountDto?{
        return AccountsEntity.select {
            AccountsEntity.accountName eq accountName
        }.firstOrNull()?.let {
            convertAccountsRowToAccountDto(it)
        }
    }

    fun getAccountsByUserId(userId: UserId): List<AccountDto> {
        return AccountsEntity.select { AccountsEntity.userId eq userId.valueLong }.map {
            convertAccountsRowToAccountDto(it)
        }
    }

    fun selectAccountsFollowedByAccountId(accountId: AccountId): List<AccountId> {
        return FollowersEntity.select {
            FollowersEntity.followingAccountId eq accountId.valueLong
        }.map {
            AccountId(it[FollowersEntity.followedAccountId])
        }
    }

    fun selectAccountIdListFollowedByAccountId(
        accountId: AccountId,
        limit: Int,
        offset: Long,
    ): List<AccountId> {
        return FollowersEntity.select {
            FollowersEntity.followingAccountId eq accountId.valueLong
        }.limit(
            limit,
            offset,
        ).orderBy(
            FollowersEntity.createdAt
        ).map {
            AccountId(it[FollowersEntity.followedAccountId])
        }
    }

    fun selectAccountsFollowedByAccountId(
        accountId: AccountId,
        limit: Int,
        offset: Long,
    ): List<AccountDto> {
        val followingSubQuery = FollowersEntity.select {
            FollowersEntity.followingAccountId eq accountId.valueLong
        }.limit(
            limit,
            offset,
        ).orderBy(
            FollowersEntity.createdAt
        ).alias("followingSubQuery")

        return AccountsEntity.join(
            followingSubQuery,
            JoinType.INNER,
            additionalConstraint = {
                AccountsEntity.accountId eq followingSubQuery[FollowersEntity.followedAccountId]
            }
        ).selectAll().map {
            convertAccountsRowToAccountDto(it)
        }
    }

    fun convertAccountsRowToAccountDto(accountRow: ResultRow): AccountDto {
        return AccountDto(
            accountId = AccountId(accountRow[AccountsEntity.accountId]),
            userId = UserId(accountRow[AccountsEntity.userId]),
            accountName = accountRow[AccountsEntity.accountName],
            displayName = accountRow[AccountsEntity.displayName],
            profileDescription = accountRow[AccountsEntity.profileDescription],
            profileImageUrl = accountRow[AccountsEntity.profileImageUrl],
            isPrimary = accountRow[AccountsEntity.isPrimary],
            createdAt = accountRow[AccountsEntity.createdAt],
            updatedAt = accountRow[AccountsEntity.updatedAt]
        )
    }
}
