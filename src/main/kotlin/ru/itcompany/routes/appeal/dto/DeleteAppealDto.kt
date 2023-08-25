package ru.itcompany.routes.appeal.dto

import kotlinx.serialization.Serializable

@Serializable
class DeleteAppealDto(
    val userDeleteId: Long,
    val deleteReason: String
)