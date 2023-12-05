package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.Users
import com.example.xt3.domain.model.dto.UserDto
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    fun getUserById(userId: UserId): UserDto? {
        return Users.select { Users.userId eq userId.value }.firstOrNull()?.let {
            UserDto(
                userId = UserId(it[Users.userId]),
                email = it[Users.email],
                pass = it[Users.pass],
                roleType = it[Users.roleType],
                createdAt = it[Users.createdAt],
                updatedAt = it[Users.updatedAt]
            )
        }
    }
}
