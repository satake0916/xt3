package com.example.xt3.domain.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable() {
    val email = varchar("email", length = 256)
    val pass = varchar("pass", length = 60)
    val role = varchar("role", length = 32)
}
