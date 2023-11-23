package com.example.xt3.domain

import org.jetbrains.exposed.dao.id.LongIdTable

object TweetEntity : LongIdTable() {
    val content = varchar("content", length = 140)
}