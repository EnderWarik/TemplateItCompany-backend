package ru.itcompany.routes.authorization.dto

import kotlinx.serialization.Serializable

@Serializable
class AuthenticateUserDto(
    val email: String,
    val password: String
)