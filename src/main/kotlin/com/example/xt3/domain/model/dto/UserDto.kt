package com.example.xt3.domain.model.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

data class UserDto(
    val id: UserId,
    val email: String,
    val pass: String,
    val role: String,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return AuthorityUtils.createAuthorityList(this.role)
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

@JvmInline
value class UserId(val value: Long)
