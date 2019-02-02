package net.nolit.dredear.controller

import net.nolit.dredear.entity.User
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.ControllerAdvice
import java.security.Principal
import java.util.*


@ControllerAdvice
class PrincipalControllerAdvice {

    @ModelAttribute
    fun getLoggedInUser(principal: Principal?): User? {
        if (principal is Authentication) {
            val user = principal.principal
            if (user is User) {
                return user
            }
        }

        return null
    }

}