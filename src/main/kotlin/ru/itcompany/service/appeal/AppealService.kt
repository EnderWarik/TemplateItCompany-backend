package ru.itcompany.service.appeal

import ru.itcompany.model.Appeal
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.DeleteAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument


interface AppealService
{
    fun getAll(): List<Appeal>

    fun findById(id: Long): Appeal?

    fun getFromTo(offset: Int, limit: Int): PaginationResponse<Appeal>

    fun create(argument: CreateAppealArgument): Appeal

    fun update(id: Long, argument: UpdateAppealArgument): Appeal

    fun delete(id: Long, argument: DeleteAppealArgument)
}