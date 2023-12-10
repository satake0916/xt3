package com.example.xt3.domain.model.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class UserDto(
    val userId: UserId,
    val email: String,
    val pass: String,
    val roleType: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return AuthorityUtils.createAuthorityList(this.roleType)
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}

data class UserId(val valueLong: Long) {
    constructor(valueStr: String) : this(valueStr.toLong())

    fun getValueStr(): String {
        return valueLong.toString()
    }
}
