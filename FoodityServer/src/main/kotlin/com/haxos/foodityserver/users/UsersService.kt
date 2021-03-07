package com.haxos.foodityserver.users

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.ws.rs.core.Response

@Service
class UsersService (
    private val keycloak: Keycloak,
    @Value("\${keycloak.realm}") private val realm: String
){
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
        representation.credentials = listOf(credRepresentation)
        representation.isEnabled = true
        return representation
    }
}