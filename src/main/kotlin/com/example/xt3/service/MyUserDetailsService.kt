package com.example.xt3.service

import com.example.xt3.domain.User
import com.example.xt3.domain.UserEntity
import com.example.xt3.domain.UserId
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
        val user = UserEntity.select { UserEntity.email eq username }.firstOrNull()?.let {
            User(
                id = UserId(it[UserEntity.id].value),
                email = it[UserEntity.email],
                pass = it[UserEntity.pass],
                role = it[UserEntity.role],
            )
        }
        return user
    }
}
