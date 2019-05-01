package net.nolit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class DredearApplication

fun main(args: Array<String>) {
	runApplication<DredearApplication>(*args)
}

