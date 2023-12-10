package com.example.xt3.domain.service

import com.example.xt3.domain.entity.UsersEntity
import com.example.xt3.domain.model.dto.UserDto
import com.example.xt3.domain.model.dto.UserId
import org.jetbrains.exposed.sql.select
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MyUserDetailsService : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails? {
        val user = UsersEntity.select { UsersEntity.email eq email }.firstOrNull()?.let {
            UserDto(
                userId = UserId(it[UsersEntity.userId]),
                email = it[UsersEntity.email],
                pass = it[UsersEntity.pass],
                roleType = it[UsersEntity.roleType],
                createdAt = it[UsersEntity.createdAt],
                updatedAt = it[UsersEntity.updatedAt]
            )
        } ?: throw UsernameNotFoundException("User not found for email: $email")

        return user
    }
}
