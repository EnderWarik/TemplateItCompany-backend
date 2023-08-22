package ru.itcompany.routes.status.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
@Serializable
class UpdateStatusDto(
    val status: StatusAppealEnum,
    val appealId: Long
)