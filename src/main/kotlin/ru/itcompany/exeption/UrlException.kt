package ru.itcompany.exeption

class UrlException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message,cause)