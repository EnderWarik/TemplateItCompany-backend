package ru.itcompany.exeption

open class NotFoundEntityException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)