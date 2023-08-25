package ru.itcompany.routes.appeal.mapper

import ru.itcompany.routes.appeal.dto.CreateAppealDto
import ru.itcompany.routes.appeal.dto.DeleteAppealDto
import ru.itcompany.routes.appeal.dto.UpdateAppealDto
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.DeleteAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument

class AppealMapper
{

    fun toUpdateStatusArgument(dto: UpdateAppealDto): UpdateAppealArgument
    {
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

    fun toDeleteAppealArgument(dto: DeleteAppealDto): DeleteAppealArgument
    {
        return DeleteAppealArgument.Builder()
            .userDeleteId(dto.userDeleteId)
            .deleteReason(dto.deleteReason)
            .build()
    }
}
