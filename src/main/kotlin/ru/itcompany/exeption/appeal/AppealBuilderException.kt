package ru.itcompany.exeption.appeal


class AppealBuilderException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)