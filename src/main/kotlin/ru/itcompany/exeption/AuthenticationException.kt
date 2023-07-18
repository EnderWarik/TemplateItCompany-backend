package ru.itcompany.exeption

class AuthenticationException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(message,cause)