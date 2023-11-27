package com.example.xt3.domain.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Tweets : LongIdTable() {
    val accountId = long("accountId")
    val text = varchar("text", length = 140)
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")
}
