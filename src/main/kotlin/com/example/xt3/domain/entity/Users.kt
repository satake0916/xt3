package com.example.xt3.domain.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Users : Table(name = "users") {
    val user_id = long("user_id").autoIncrement()
    val email = varchar("email", length = 256)
    val pass = varchar("pass", length = 60)
    val roleType = varchar("role_type", length = 32)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(user_id)
}
