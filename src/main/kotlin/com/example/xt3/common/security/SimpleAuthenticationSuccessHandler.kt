package com.example.xt3.common.security

import com.example.xt3.domain.model.dto.UserDto
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.io.IOException

class SimpleAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        auth: Authentication
    ) {
        if (response.isCommitted) {
            println("Response has already been committed.")
            return
        }
        val userDto = auth.principal as UserDto
        response.writer.write("{\"userId\":${userDto.userId.value}}")
        response.status = HttpStatus.OK.value()
        clearAuthenticationAttributes(request)
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the
     * session during the authentication process.
     */
    private fun clearAuthenticationAttributes(request: HttpServletRequest) {
        val session = request.getSession(false) ?: return
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
    }
}
