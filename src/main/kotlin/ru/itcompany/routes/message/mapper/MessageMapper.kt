package ru.itcompany.routes.message.mapper

import ru.itcompany.routes.appeal.dto.CreateAppealDto
import ru.itcompany.routes.appeal.dto.UpdateAppealDto
import ru.itcompany.routes.message.dto.CreateMessageDto
import ru.itcompany.routes.message.dto.UpdateMessageDto
import ru.itcompany.routes.status.dto.CreateStatusDto
import ru.itcompany.routes.status.dto.UpdateStatusDto
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.message.argument.CreateMessageArgument
import ru.itcompany.service.message.argument.UpdateMessageArgument
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument

class MessageMapper {

    fun toUpdateMessageArgument(dto: UpdateMessageDto): UpdateMessageArgument    {
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
