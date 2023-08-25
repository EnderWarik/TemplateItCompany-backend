package ru.itcompany.service.status

import ru.itcompany.model.Status
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument


interface StatusService
{
    fun getAll(): List<Status>

    fun create(argument: CreateStatusArgument): Status

    fun getFromTo(offset: Int, limit: Int): PaginationResponse<Status>

    fun update(id: Long, argument: UpdateStatusArgument): Status

    fun delete(id: Long)
}