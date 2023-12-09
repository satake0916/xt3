package com.example.xt3.domain.repository

import com.example.xt3.domain.entity.UsersEntity
import com.example.xt3.domain.model.dto.UserDto
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    fun getUserById(userId: UserId): UserDto? {
        return UsersEntity.select { UsersEntity.userId eq userId.valueLong }.firstOrNull()?.let {
            UserDto(
                userId = UserId(it[UsersEntity.userId]),
                email = it[UsersEntity.email],
                pass = it[UsersEntity.pass],
                roleType = it[UsersEntity.roleType],
                createdAt = it[UsersEntity.createdAt],
                updatedAt = it[UsersEntity.updatedAt]
            )
        }
    }
}
