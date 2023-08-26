package ru.itcompany.controller.user.response

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.UserRoleEnum

@Serializable
data class GetUserResponse(
    val id: Int,
    val email: String,
    val password: String,
    val role: UserRoleEnum,
    val firstName: String,
    val lastName: String,
    val thirdName: String,
    val address: String,
    val phoneNumber: String,
    val inn: String,
    val organizationName: String,
    val isDeleted: Boolean,
    val dateCreate: String
)