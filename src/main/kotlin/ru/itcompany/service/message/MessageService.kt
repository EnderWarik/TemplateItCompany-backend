package ru.itcompany.service.message

import ru.itcompany.model.Message
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.message.argument.CreateMessageArgument
import ru.itcompany.service.message.argument.UpdateMessageArgument


interface MessageService
{
    fun getAll(): List<Message>

    fun create(argument: CreateMessageArgument): Message

    fun getFromTo(offset: Int, limit: Int): PaginationResponse<Message>

    fun update(id: Long, argument: UpdateMessageArgument): Message

    fun delete(id: Long)
}