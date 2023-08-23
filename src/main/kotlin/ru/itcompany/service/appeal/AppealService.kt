package ru.itcompany.service.appeal

import ru.itcompany.model.Appeal
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument


interface AppealService {
    fun getAll(): List<Appeal>

    fun create(argument: CreateAppealArgument): Appeal

    fun update(id: Long, argument: UpdateAppealArgument): Appeal

    fun delete(id: Long)
}