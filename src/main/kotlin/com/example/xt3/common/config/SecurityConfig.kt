package com.example.xt3.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authz ->
            authz
                .anyRequest()
                .permitAll()
        }
        /* TODO ログイン処理をREST APIでも実装する。Spring Securityの勉強から。
        .formLogin { login -> login
            .loginProcessingUrl("/login")
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("pass")
            .failureUrl("/login?error")
            .permitAll()
        }*/

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
