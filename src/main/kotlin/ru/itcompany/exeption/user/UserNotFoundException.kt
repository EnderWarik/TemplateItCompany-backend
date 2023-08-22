package ru.itcompany.exeption.user

import ru.itcompany.exeption.user.UserException

class UserNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : UserException(message,cause)