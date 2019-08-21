package net.nolit.controller.form

import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class UserCreateRequest {
    @NotBlank(message = "メールアドレスは必須項目です")
    @Email(message = "メールアドレスの形式で入力してください")
    lateinit var email: String

    @NotBlank(message = "パスワードは必須項目です")
    @Min(value = 8, message = "パスワードは最低8文字です")
    lateinit var password: String

    @NotBlank(message = "名前は必須項目です")
    @Max(value = 16, message = "名前は16文字以内で入力してください")
    lateinit var userName: String
}