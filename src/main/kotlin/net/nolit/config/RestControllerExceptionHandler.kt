package net.nolit.config

import net.nolit.exception.ValidationErrorException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RestControllerExceptionHandler {
    @ExceptionHandler(ValidationErrorException::class)
    fun handleValidationErrorException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        val validationException = ex as ValidationErrorException
        return ResponseEntity(validationException.errors.allErrors, headers, status)
    }
}