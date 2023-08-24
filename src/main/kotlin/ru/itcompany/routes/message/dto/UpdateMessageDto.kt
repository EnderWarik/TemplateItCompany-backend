package ru.itcompany.routes.message.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
@Serializable
class UpdateMessageDto(
    val appealId: Long,
    val ownerId: Long,
    val content: String
)