package ru.itcompany.service.message.argument

import ru.itcompany.exeption.message.MessageBuilderException

class CreateMessageArgument private constructor(
    val appealId: Long,
    val ownerId: Long,
    val content: String

)
{
    data class Builder(
        var appealId: Long = -1,
        var ownerId: Long = -1,
        var content: String = ""
    )
    {

        fun appealId(appealId: Long) = apply { this.appealId = appealId }
        fun ownerId(ownerId: Long) = apply { this.ownerId = ownerId }
        fun content(content: String) = apply { this.content = content }

        fun build(): CreateMessageArgument
        {
            if (appealId <= 0)
            {
                throw MessageBuilderException("Appeal id is not correct")
            }
            if (ownerId <= 0)
            {
                throw MessageBuilderException("User owner id is not correct")
            }
            return CreateMessageArgument(appealId, ownerId, content)
        }
    }


}