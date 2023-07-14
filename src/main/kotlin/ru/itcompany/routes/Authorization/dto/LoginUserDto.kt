package ru.itcompany.routes.Authorization.dto

import kotlinx.serialization.Serializable
import java.util.*
@Serializable
class LoginUserDto(
    val email: String,
    val password: String
)