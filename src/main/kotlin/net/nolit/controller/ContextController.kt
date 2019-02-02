package net.nolit.dredear.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/context")
class ContextController {
    @GetMapping("/time")
    fun getTime(): Date {
        return Date()
    }
}