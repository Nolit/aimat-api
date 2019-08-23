package net.nolit.config

import net.nolit.exception.DomainException
import net.nolit.exception.ValidationErrorException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

data class Error(val field: String, val message: String?)

@RestControllerAdvice
class RestControllerExceptionHandler {
    @ExceptionHandler(ValidationErrorException::class)
    fun handleValidationErrorException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        val validationException = ex as ValidationErrorException
        val errors = validationException.errors.fieldErrors.map { error -> Error(field = error.field, message = error.defaultMessage) }
        return ResponseEntity(mutableMapOf("errors" to errors), headers, status)
    }

    @ExceptionHandler(DomainException::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity(mutableMapOf("error" to ex.message), headers, status)
    }
}