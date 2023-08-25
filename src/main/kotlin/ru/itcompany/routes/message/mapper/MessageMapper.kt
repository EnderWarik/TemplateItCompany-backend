package ru.itcompany.routes.message.mapper

import ru.itcompany.routes.message.dto.CreateMessageDto
import ru.itcompany.routes.message.dto.UpdateMessageDto
import ru.itcompany.service.message.argument.CreateMessageArgument
import ru.itcompany.service.message.argument.UpdateMessageArgument

class MessageMapper
{

    fun toUpdateMessageArgument(dto: UpdateMessageDto): UpdateMessageArgument
    {
        return UpdateMessageArgument.Builder()
            .appealId(dto.appealId)
            .ownerId(dto.ownerId)
            .content(dto.content)
            .build()
    }

    fun toCreateMessageArgument(dto: CreateMessageDto): CreateMessageArgument
    {
        return CreateMessageArgument.Builder()
            .appealId(dto.appealId)
            .ownerId(dto.ownerId)
            .content(dto.content)
            .build()
    }
}
