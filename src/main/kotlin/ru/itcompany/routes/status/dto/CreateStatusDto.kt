package ru.itcompany.routes.status.dto

import kotlinx.serialization.Serializable
import ru.itcompany.model.enum.StatusAppealEnum

@Serializable
class CreateStatusDto(
    val status: StatusAppealEnum,
    val appealId: Long
)