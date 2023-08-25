package ru.itcompany.exeption.user

class UserNotAccessException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)