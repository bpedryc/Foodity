package com.haxos.foodityserver;

import org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class KeycloakConfiguration (
    @Value("\${keycloak.credentials.secret}")
    private val clientSecret: String,
    @Value("\${keycloak.resource}")
    private val clientId: String,
    @Value("\${keycloak.auth-server-url}")
    private val authUrl: String,
    @Value("\${keycloak.realm}")
    private val realm: String
) {

    @Bean
    @Qualifier("openidApi")
    fun openIdApiClient(): WebClient {
        return WebClient.create("http://localhost:8180/auth/realms/FoodityKeycloak/protocol/openid-connect")
    }

    @Bean
    @Qualifier("keycloakApi")
    fun keycloakApiClient(): WebClient {
        return WebClient.create("http://localhost:8180/auth/realms/FoodityKeycloak")
    }

    @Bean
    fun provideKeycloakBuilder(): KeycloakBuilder {
        return KeycloakBuilder.builder()
            .serverUrl(authUrl)
            .realm(realm)
            .clientId(clientId)
            .clientSecret(clientSecret)
    }

    @Bean
    fun provideKeycloak(builder: KeycloakBuilder): Keycloak {
        return builder
            .grantType(CLIENT_CREDENTIALS)
            .build()
    }
}
