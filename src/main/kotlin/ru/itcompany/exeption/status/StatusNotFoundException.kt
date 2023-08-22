package ru.itcompany.exeption.status

import ru.itcompany.exeption.user.UserException

class StatusNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message,cause)