package ru.itcompany.routes.authorization.dto

import kotlinx.serialization.Serializable
import ru.itcompany.models.enum.UserRoleEnum

@Serializable
class RegisterUserDto(
    val email: String,
    val password: String,
    val role: UserRoleEnum,
    val firstName: String,
    val lastName: String,
    val thirdName: String?,
    val address: String?,
    val phoneNumber: String?,
    val inn: String?,
    val organizationName: String?
)