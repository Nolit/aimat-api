package net.nolit.controller.validator

import net.nolit.controller.form.UserCreateRequest
import net.nolit.dredear.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class UserCreateValidator(
        private val repository: UserRepository
): AbstractValidator<UserCreateRequest>(){
    override fun doValidate(form: UserCreateRequest, errors: Errors) {
        if (repository.findByEmail(form.email) !== null) {
            errors.rejectValue("email", "messages.email.unique", "メールアドレスは既に登録されています。")
        }
    }
}