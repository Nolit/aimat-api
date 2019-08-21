package net.nolit.controller.validator

import org.springframework.validation.Errors
import org.springframework.validation.Validator

abstract class AbstractValidator<T>: Validator {
    override fun supports(p0: Class<*>): Boolean {
        return true
    }

    override fun validate(target: Any, errors: Errors) {
        val hasErrors = errors.hasErrors()
        if (!hasErrors) {
            doValidate(target as T, errors)
        }
    }

    protected abstract fun doValidate(form: T, errors: Errors)
}