package ru.itcompany.exeption.message

import ru.itcompany.exeption.NotFoundEntityException


class MessageNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : NotFoundEntityException(message, cause)