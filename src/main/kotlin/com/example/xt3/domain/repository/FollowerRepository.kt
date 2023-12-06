package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.Followers
import com.example.xt3.domain.model.dto.AccountId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class FollowerRepository {
    fun getAccountsFollowedByAccountId(accountId: AccountId): List<AccountId> {
        return Followers.select { Followers.followingAccountId eq accountId.value }.map {
            AccountId(it[Followers.followedAccountId])
        }
    }
}
