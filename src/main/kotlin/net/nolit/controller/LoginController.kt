package net.nolit.dredear.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("loginForm")
class LoginController {
    @GetMapping
    fun loginForm(): String {
        return "Login Page"
    }
}