package ru.itcompany.exeption

class UserNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : UserException(message,cause)