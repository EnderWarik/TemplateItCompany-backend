package ru.itcompany.routes.appeal.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
@Serializable
class CreateAppealDto(
    val userCreatorId: Long,
    val userEmployeeId: Long?,
    val statusId: Long,
    val title: String
)