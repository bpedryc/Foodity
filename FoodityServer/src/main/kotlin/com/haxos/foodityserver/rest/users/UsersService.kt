package com.haxos.foodityserver.rest.users

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import javax.ws.rs.core.Response

@Service
class UsersService (
    private val keycloak: Keycloak,
    @Value("\${keycloak.realm}") private val realm: String,
    @Qualifier("keycloakApi") private val client: WebClient
){
    private fun getUser(id: String): UserResource {
        return keycloak
            .realm(realm)
            .users()
            .get(id)
    }

    private fun getUserId(username: String): String? {
        return keycloak
            .realm(realm)
            .users()
            .search(username)
            .firstOrNull()
            ?.id
    }

    private fun getUserByUsername(username: String) : UserResource? {
        val userId = getUserId(username) ?: return null
        return getUser(userId)
    }

    fun sendVerificationEmail(username: String) {
        val userId = getUserId(username)
        client.put()
            .uri { uriBuilder -> uriBuilder
                .path("/users/{id}/send-verify-email")
                .build(userId) }
            .retrieve()
    }

    fun getAllUsers() : List<User> {
        val keycloakUsers : List<UserRepresentation> = keycloak
            .realm(realm)
            .users()
            .list()
        return keycloakUsers.map {
            User(it.id, it.username, it.email ?: "", "", null, emptyList())
        }
    }

    fun getUserRolesByUsername(username: String) : List<String> {
        val user = getUserByUsername(username) ?: return emptyList()
        return user
            .roles()
            .realmLevel()
            .listAll()
            .map { it.name }
    }

    fun createUser(request: UserRequest): Response {
        val credentialRepresentation = prepareCredentialRepresentation(request.password)
        val user = prepareUserRepresentation(request, credentialRepresentation)

        return keycloak
            .realm(realm)
            .users()
            .create(user)
    }

    private fun prepareCredentialRepresentation(password: String): CredentialRepresentation {
        val representation = CredentialRepresentation()
        representation.isTemporary = false
        representation.type = CredentialRepresentation.PASSWORD
        representation.value = password
        return representation
    }

    private fun prepareUserRepresentation(request: UserRequest, credRepresentation: CredentialRepresentation): UserRepresentation {
        val representation = UserRepresentation()
        representation.username = request.username
        representation.email = request.email
        representation.credentials = listOf(credRepresentation)
        representation.isEnabled = true
        return representation
    }
}