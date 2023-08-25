package ru.itcompany.routes.appeal.dto

import kotlinx.serialization.Serializable

@Serializable
class CreateAppealDto(
    val userCreatorId: Long,
    val userEmployeeId: Long?,
    val statusId: Long,
    val title: String
)