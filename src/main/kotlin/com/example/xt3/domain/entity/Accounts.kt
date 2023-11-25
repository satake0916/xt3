package com.example.xt3.domain.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object Accounts : LongIdTable() {
    val userId = long("userId")
    val displayName = varchar("displayName", length = 256)
    val description = varchar("description", length = 512)
    val isPrimary = bool("isPrimary")
}
