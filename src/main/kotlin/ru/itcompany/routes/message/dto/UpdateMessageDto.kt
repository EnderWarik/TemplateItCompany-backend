package ru.itcompany.routes.message.dto

import kotlinx.serialization.Serializable

@Serializable
class UpdateMessageDto(
    val appealId: Long,
    val ownerId: Long,
    val content: String
)