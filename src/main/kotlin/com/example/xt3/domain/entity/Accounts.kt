package com.example.xt3.domain.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object Accounts : LongIdTable() {
    val userId = long("userId")
    val displayName = varchar("email", length = 256)
    val description = varchar("pass", length = 512)
    val isPrimary = bool("isPrimary")
}
