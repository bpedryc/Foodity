package com.haxos.foodityserver.rest.auth

import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@Component
@Service
class AuthService (
    private val keycloakBuilder: KeycloakBuilder,
    @Value("\${keycloak.resource}") private val clientId: String,
    @Value("\${keycloak.credentials.secret}") private val clientSecret: String,
    @Qualifier("openidApi") private val client: WebClient
){
    val REQUEST_TIMEOUT: Duration = Duration.ofSeconds(60)

    fun getToken(username: String?, password: String?): Token? {
        if (username == null || password == null) {
            return null
        }
        return client.post()
            .uri("/token")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(
                BodyInserters
                    .fromFormData("client_id", clientId)
                    .with("username", username)
                    .with("password", password)
                    .with("grant_type", "password")
                    .with("client_secret", clientSecret))
            .retrieve()
            .bodyToMono(Token::class.java)
            .block(REQUEST_TIMEOUT)
    }

    fun refreshToken(refreshToken: String?): Token? {
        if (refreshToken == null) {
            return null;
        }
        return client.post()
            .uri("/token")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(
                BodyInserters
                    .fromFormData("client_id", clientId)
                    .with("refresh_token", refreshToken)
                    .with("grant_type", "refresh_token")
                    .with("client_secret", clientSecret))
            .retrieve()
            .bodyToMono(Token::class.java)
            .block(REQUEST_TIMEOUT)
    }
}