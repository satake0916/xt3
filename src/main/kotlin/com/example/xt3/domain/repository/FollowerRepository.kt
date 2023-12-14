package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.FollowersEntity
import com.example.xt3.domain.model.dto.AccountId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
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

    fun deleteFollowers(sourceAccountId: AccountId, targetAccountId: AccountId): Int {
        return FollowersEntity.deleteWhere {
            followingAccountId eq sourceAccountId.valueLong and
                (followedAccountId eq targetAccountId.valueLong)
        }
    }
}
