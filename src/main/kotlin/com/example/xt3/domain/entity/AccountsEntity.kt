package com.example.xt3.domain.entity

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object AccountsEntity : Table(name = "accounts") {
    val accountId = long("account_id").autoIncrement()
    val userId = long("user_id").references(
        UsersEntity.userId,
        fkName = "fk_user_id",
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.RESTRICT
    )
    val accountName = varchar("account_name", length = 15).uniqueIndex()
    val displayName = varchar("display_name", length = 50)
    val profileDescription = varchar("profile_description", length = 500).default("")
    val profileImageUrl = varchar("profile_image_url", length = 200).nullable()
    val isPrimary = bool("is_primary")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(accountId)
}
