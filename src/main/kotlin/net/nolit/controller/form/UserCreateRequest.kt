package net.nolit.controller.form

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class UserCreateRequest {
    @NotBlank
    @Email
    lateinit var email: String
    @NotBlank
    lateinit var password: String
    @NotBlank
    lateinit var userName: String
}