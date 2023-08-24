package ru.itcompany.routes.appeal.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
@Serializable
class DeleteAppealDto(
    val userDeleteId: Long,
    val deleteReason: String
)