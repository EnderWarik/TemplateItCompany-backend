package ru.itcompany.service.user.response

import ru.itcompany.model.User

data class UsersResponse(
    val values: List<User>,
    val totalRecords: Int
)