package com.example.xt3.domain.service

import com.example.xt3.domain.entity.Users
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
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = Users.select { Users.email eq username }.firstOrNull()?.let {
            UserDto(
                userId = UserId(it[Users.user_id]),
                email = it[Users.email],
                pass = it[Users.pass],
                roleType = it[Users.roleType],
                createdAt = it[Users.createdAt],
                updatedAt = it[Users.updatedAt]
            )
        }
        return user
    }
}
