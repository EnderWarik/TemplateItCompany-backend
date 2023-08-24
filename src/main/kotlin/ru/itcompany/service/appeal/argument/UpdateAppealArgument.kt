package ru.itcompany.service.appeal.argument

import ru.itcompany.exeption.appeal.AppealBuilderException
import ru.itcompany.exeption.status.StatusBuilderException
import ru.itcompany.model.Appeal
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
import java.time.Instant

class UpdateAppealArgument private constructor(
    val userCreatorId: Long,
    val userEmployeeId: Long?,
    val statusId: Long,
    val title: String
) {


    data class Builder(
        var userCreatorId: Long = -1,
        var userEmployeeId: Long? = null,
        var statusId: Long = -1,
        var title: String = ""
    ) {

        fun userCreatorId(userCreatorId: Long) = apply { this.userCreatorId = userCreatorId }
        fun userEmployeeId(userEmployeeId: Long?) = apply { this.userEmployeeId = userEmployeeId }
        fun statusId(statusId: Long) = apply { this.statusId = statusId }
        fun title(title: String) = apply { this.title = title }

       fun build(): UpdateAppealArgument {
            if(userCreatorId <= 0)
            {
                throw AppealBuilderException("User creator id is not correct")
            }
           if(statusId <= 0)
           {
               throw AppealBuilderException("Status id is not correct")
           }
            return UpdateAppealArgument( userCreatorId, userEmployeeId, statusId, title)
        }
    }



}