package ru.itcompany.routes.status.mapper

import ru.itcompany.routes.status.dto.CreateStatusDto
import ru.itcompany.routes.status.dto.UpdateStatusDto
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument

class StatusMapper
{

    fun toUpdateStatusArgument(dto: UpdateStatusDto): UpdateStatusArgument
    {
        return UpdateStatusArgument.Builder()
            .status(dto.status)
            .appealId(dto.appealId)
            .build()
    }

    fun toCreateStatusArgument(dto: CreateStatusDto): CreateStatusArgument
    {
        return CreateStatusArgument.Builder()
            .status(dto.status)
            .appealId(dto.appealId)
            .build()
    }
}
