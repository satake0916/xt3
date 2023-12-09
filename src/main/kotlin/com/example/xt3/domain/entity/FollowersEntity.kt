package com.example.xt3.domain.entity

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object FollowersEntity : Table(name = "followers") {
    val followingAccountId = long("following_account_id").references(
        AccountsEntity.accountId,
        fkName = "fk_account_id",
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.RESTRICT
    )
    val followedAccountId = long("followed_account_id").references(
        AccountsEntity.accountId,
        fkName = "fk_account_id",
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.RESTRICT
    )

    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(followingAccountId, followedAccountId)
}
