package com.example.xt3.domain.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object Tweets : LongIdTable() {
    val accountId = long("accountId")
    val text = varchar("text", length = 140)
}
