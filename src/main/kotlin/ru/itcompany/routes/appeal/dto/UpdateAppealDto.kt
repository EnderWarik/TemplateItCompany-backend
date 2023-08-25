package ru.itcompany.routes.appeal.dto

import kotlinx.serialization.Serializable

@Serializable
class UpdateAppealDto(
    val userCreatorId: Long,
    val userEmployeeId: Long?,
    val statusId: Long,
    val title: String
)