package ru.itcompany.exeption

class DataBaseException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message,cause)