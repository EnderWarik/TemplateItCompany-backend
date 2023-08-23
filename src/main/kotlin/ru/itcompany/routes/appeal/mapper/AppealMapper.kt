package ru.itcompany.routes.appeal.mapper

import ru.itcompany.routes.appeal.dto.CreateAppealDto
import ru.itcompany.routes.appeal.dto.UpdateAppealDto
import ru.itcompany.routes.status.dto.CreateStatusDto
import ru.itcompany.routes.status.dto.UpdateStatusDto
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument

class AppealMapper {

    fun toUpdateStatusArgument(dto: UpdateAppealDto): UpdateAppealArgument    {
        return UpdateAppealArgument.Builder()
            .userCreatorId(dto.userCreatorId)
            .statusId(dto.statusId)
            .title(dto.title)
            .userEmployeeId(dto.userEmployeeId)
            .build()
    }
    fun toCreateAppealArgument(dto: CreateAppealDto): CreateAppealArgument
    {
        return CreateAppealArgument.Builder()
            .userCreatorId(dto.userCreatorId)
            .statusId(dto.statusId)
            .title(dto.title)
            .userEmployeeId(dto.userEmployeeId)
            .build()
    }
}
