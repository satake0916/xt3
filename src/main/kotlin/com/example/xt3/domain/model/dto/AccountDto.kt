package com.example.xt3.domain.model.dto

import java.time.LocalDateTime

data class AccountDto(
    val accountId: AccountId,
    val userId: UserId,
    val accountName: String,
    val displayName: String,
    val profileDescription: String,
    val profileImageUrl: String?,
    val isPrimary: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

@JvmInline
value class AccountId(val value: Long)
