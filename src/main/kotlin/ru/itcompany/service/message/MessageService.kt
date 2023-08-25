package ru.itcompany.service.message

import ru.itcompany.model.Appeal
import ru.itcompany.model.Message
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.message.argument.CreateMessageArgument
import ru.itcompany.service.message.argument.UpdateMessageArgument
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument



interface MessageService {
    fun getAll(): List<Message>

    fun create(argument: CreateMessageArgument): Message

    fun getFromTo(offset: Int, limit: Int): PaginationResponse<Message>

    fun update(id: Long, argument: UpdateMessageArgument): Message

    fun delete(id: Long)
}