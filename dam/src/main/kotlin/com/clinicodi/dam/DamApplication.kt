package com.clinicodi.dam

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DamApplication

fun main(args: Array<String>) {
	runApplication<DamApplication>(*args)
}
