package com.example.xt3.domain.entity

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TweetsEntity : Table(name = "tweets") {
    val tweetId = long("tweet_id").autoIncrement()
    val accountId = long("account_id").references(
        AccountsEntity.accountId,
        fkName = "fk_account_id",
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.RESTRICT
    )
    val tweetText = varchar("tweet_text", length = 140)
    val parentTweetId = long("parent_tweet_id").references(
        tweetId,
        fkName = "fk_tweet_id"
    ).nullable()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(tweetId)
}
