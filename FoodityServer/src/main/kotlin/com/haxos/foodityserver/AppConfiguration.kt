package com.haxos.foodityserver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AppConfiguration {
    @Bean
    fun keycloakApiClient(): WebClient {
        return WebClient.create("http://localhost:8180/auth/realms/FoodityKeycloak/protocol/openid-connect")
    }
}