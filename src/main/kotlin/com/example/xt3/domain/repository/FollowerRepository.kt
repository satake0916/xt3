package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.FollowersEntity
import com.example.xt3.domain.model.dto.AccountId
import org.jetbrains.exposed.sql.insert
import org.springframework.stereotype.Repository

@Repository
class FollowerRepository {
    fun insertFollowers(followingAccountId: AccountId, followedAccountId: AccountId) {
        FollowersEntity.insert {
            it[FollowersEntity.followingAccountId] = followingAccountId.valueLong
            it[FollowersEntity.followedAccountId] = followedAccountId.valueLong
        }
    }
}
