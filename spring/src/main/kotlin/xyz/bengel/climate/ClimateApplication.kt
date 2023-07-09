package xyz.bengel.climate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClimateApplication

fun main(args: Array<String>) {
	runApplication<ClimateApplication>(*args)
}
