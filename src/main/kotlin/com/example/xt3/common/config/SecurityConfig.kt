package com.example.xt3.common.config

import com.example.xt3.common.security.SimpleAccessDeniedHandler
import com.example.xt3.common.security.SimpleAuthenticationEntryPoint
import com.example.xt3.common.security.SimpleAuthenticationFailureHandler
import com.example.xt3.common.security.SimpleAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { requests ->
            requests
                // .requestMatchers("/tweets").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
        }.csrf { csrf ->
            csrf.disable()
        }.cors { cors ->
            cors.configurationSource(corsConfigurationSource())
        }.formLogin { login ->
            login
                .loginProcessingUrl("/v1/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("pass")
                .successHandler(SimpleAuthenticationSuccessHandler())
                .failureHandler(SimpleAuthenticationFailureHandler())
        }.logout { logout ->
            logout
                .logoutUrl("/v1/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler())
        }.exceptionHandling { ex ->
            ex
                .authenticationEntryPoint(SimpleAuthenticationEntryPoint())
                .accessDeniedHandler(SimpleAccessDeniedHandler())
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowCredentials = true
        configuration.allowedOrigins = listOf("http://localhost:3000")
        configuration.allowedMethods = listOf("GET", "POST", "OPTIONS")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
