package com.example.xt3.domain.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UsersEntity : Table(name = "users") {
    val userId = long("user_id").autoIncrement()
    val email = varchar("email", length = 256)
    val pass = varchar("pass", length = 60)
    val roleType = varchar("role_type", length = 32)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(userId)
}
