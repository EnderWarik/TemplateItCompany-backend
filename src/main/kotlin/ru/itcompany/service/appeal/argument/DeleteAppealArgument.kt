package ru.itcompany.service.appeal.argument

import ru.itcompany.exeption.appeal.AppealBuilderException

class DeleteAppealArgument private constructor(
    val userDeleteId: Long,
    val deleteReason: String
)
{


    data class Builder(
        var userDeleteId: Long = -1,
        var deleteReason: String = ""
    )
    {

        fun userDeleteId(userDeleteId: Long) = apply { this.userDeleteId = userDeleteId }
        fun deleteReason(deleteReason: String) = apply { this.deleteReason = deleteReason }

        fun build(): DeleteAppealArgument
        {
            if (userDeleteId <= 0)
            {
                throw AppealBuilderException("User delete id is not correct")
            }
            if (deleteReason == "")
            {
                throw AppealBuilderException("Delete reason is not correct")
            }
            return DeleteAppealArgument(userDeleteId, deleteReason)
        }
    }


}