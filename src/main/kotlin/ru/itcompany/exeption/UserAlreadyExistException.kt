package ru.itcompany.exeption

import java.util.*

class UserAlreadyExistException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : UserException(message,cause)