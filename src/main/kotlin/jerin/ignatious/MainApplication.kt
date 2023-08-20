package jerin.ignatious

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["jerin.ignatious"]
)
class MainApplication
fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    runApplication<MainApplication>(*args)
}
