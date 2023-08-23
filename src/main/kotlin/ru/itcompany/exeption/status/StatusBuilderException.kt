package ru.itcompany.exeption.status

class StatusBuilderException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message,cause)