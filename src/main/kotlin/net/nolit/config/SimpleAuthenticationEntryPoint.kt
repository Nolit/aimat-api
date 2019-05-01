package net.nolit.dredear.config

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SimpleAuthenticationEntryPoint: AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException?) {
        if (response.isCommitted) {
            println("isCommited")
            return;
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "許可されていないアクションです");

    }
}