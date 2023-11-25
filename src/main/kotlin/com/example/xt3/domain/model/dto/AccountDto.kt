package com.example.xt3.domain.model.dto

data class AccountDto(
    val id: AccountId,
    val userId: UserId,
    val displayName: String,
    val description: String,
)

@JvmInline
value class AccountId(val value: Long)
