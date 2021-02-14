package com.haxos.foodityserver.auth

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import java.time.Duration
import org.springframework.web.reactive.function.client.WebClient

@Component
@Service
class AuthService (
    private val client: WebClient
){
    val REQUEST_TIMEOUT: Duration = Duration.ofSeconds(3)

    fun getToken(clientId: String, username: String, password: String): Token? {
        return client.post()
            .uri("/token")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(
                BodyInserters
                    .fromFormData("client_id", clientId)
                    .with("username", username)
                    .with("password", password)
                    .with("grant_type", "password"))
            .retrieve()
            .bodyToMono(Token::class.java)
            .block(REQUEST_TIMEOUT)
    }
}