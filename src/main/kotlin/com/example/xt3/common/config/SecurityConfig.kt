package com.example.xt3.common.config

import com.example.xt3.common.security.SimpleAccessDeniedHandler
import com.example.xt3.common.security.SimpleAuthenticationFailureHandler
import com.example.xt3.common.security.SimpleAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { requests ->
            requests
                .anyRequest().authenticated()
        }.csrf { csrf ->
            csrf.disable()
        }.formLogin { login ->
            login
                .loginProcessingUrl("/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("pass")
                .successHandler(SimpleAuthenticationSuccessHandler())
                .failureHandler(SimpleAuthenticationFailureHandler())
        }.logout { logout ->
            logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler())
        }.exceptionHandling { ex ->
            ex
                .accessDeniedHandler(SimpleAccessDeniedHandler())
                .accessDeniedHandler(SimpleAccessDeniedHandler())
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
