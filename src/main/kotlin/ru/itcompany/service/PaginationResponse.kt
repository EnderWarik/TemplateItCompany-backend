package ru.itcompany.service

import ru.itcompany.model.Meta

data class PaginationResponse<T>(
    val data: List<T>,
    val meta: Meta
)