package ru.itcompany.service.message.argument

import ru.itcompany.exeption.appeal.AppealBuilderException
import ru.itcompany.exeption.message.MessageBuilderException
import ru.itcompany.exeption.status.StatusBuilderException
import ru.itcompany.model.Appeal
import ru.itcompany.model.User
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
import java.time.Instant

class UpdateMessageArgument private constructor(
    val appealId: Long,
    val ownerId: Long,
    val content: String

) {
    data class Builder(
        var appealId: Long = -1,
        var ownerId: Long = -1,
        var content: String = ""
    ) {

        fun appealId(appealId: Long) = apply { this.appealId = appealId }
        fun ownerId(ownerId: Long) = apply { this.ownerId = ownerId }
        fun content(content: String) = apply { this.content = content }

       fun build(): UpdateMessageArgument {
            if(appealId <= 0)
            {
                throw MessageBuilderException("Appeal id is not correct")
            }
           if(ownerId <= 0)
           {
               throw MessageBuilderException("User owner id is not correct")
           }
            return UpdateMessageArgument( appealId, ownerId, content)
        }
    }



}