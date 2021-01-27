package com.haxos.foodityserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodityServerApplication

fun main(args: Array<String>) {
	runApplication<FoodityServerApplication>(*args)
}
