package com.example.xt3

import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@ImportAutoConfiguration(ExposedAutoConfiguration::class)
class Xt3Application

fun main(args: Array<String>) {
	@Suppress("SpreadOperator")
	runApplication<Xt3Application>(*args)
}
