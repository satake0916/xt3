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

data class AccountId(
    val valueLong: Long
) {
    constructor(valueStr: String) : this(valueStr.toLong())

    fun getValueStr(): String {
        return valueLong.toString()
    }
}
