package ru.itcompany.routes.message.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
@Serializable
class UpdateMessageDto(
    var appealId: Long,
    var ownerId: Long,
    var content: String
)