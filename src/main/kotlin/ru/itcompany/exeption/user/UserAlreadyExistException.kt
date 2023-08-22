package ru.itcompany.exeption.user

class UserAlreadyExistException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : UserException(message,cause)