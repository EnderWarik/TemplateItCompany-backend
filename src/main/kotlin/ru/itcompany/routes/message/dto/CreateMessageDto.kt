package ru.itcompany.routes.message.dto

import kotlinx.serialization.Serializable

@Serializable
class CreateMessageDto(
    val appealId: Long,
    val ownerId: Long,
    val content: String
)