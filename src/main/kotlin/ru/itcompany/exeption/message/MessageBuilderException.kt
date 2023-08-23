package ru.itcompany.exeption.message

class MessageBuilderException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message,cause)