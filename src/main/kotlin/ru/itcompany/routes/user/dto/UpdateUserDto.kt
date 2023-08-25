package ru.itcompany.routes.user.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.UserRoleEnum

@Serializable
class UpdateUserDto(
    val email: String,
    val password: String,
    val role: UserRoleEnum,
    val firstName: String,
    val lastName: String,
    val thirdName: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val inn: String? = null,
    val organizationName: String? = null
)