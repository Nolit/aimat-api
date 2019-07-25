package net.nolit.exception

import org.springframework.validation.Errors

class ValidationErrorException(val errors: Errors): Exception() {
}