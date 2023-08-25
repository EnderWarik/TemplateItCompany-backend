package ru.itcompany.service.status.argument

import ru.itcompany.exeption.status.StatusBuilderException
import ru.itcompany.model.enum.StatusAppealEnum

class CreateStatusArgument private constructor(
    var status: StatusAppealEnum,
    var appealId: Long,
)
{

    data class Builder(
        var status: StatusAppealEnum = StatusAppealEnum.PendingReview,
        var appealId: Long = -1
    )
    {

        fun status(status: StatusAppealEnum) = apply { this.status = status }
        fun appealId(appealId: Long) = apply { this.appealId = appealId }

        fun build(): CreateStatusArgument
        {
            if (appealId <= 0)
            {
                throw StatusBuilderException("Appeal id is not correct")
            }
            return CreateStatusArgument(status, appealId)
        }
    }


}