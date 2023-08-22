package ru.itcompany.service.status

import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument


interface StatusService {
    fun getAll(): List<Status>

    fun create(argument: CreateStatusArgument): Status

    fun update(id: Long, argument: UpdateStatusArgument): Status

    fun delete(id: Long)
}