package com.haxos.foodityserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FileStorageConfiguration::class)
class FoodityServerApplication

fun main(args: Array<String>) {
	runApplication<FoodityServerApplication>(*args)
}

