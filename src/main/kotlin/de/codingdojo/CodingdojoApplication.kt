package de.codingdojo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodingdojoApplication

fun main(args: Array<String>) {
    runApplication<CodingdojoApplication>(*args)
}
