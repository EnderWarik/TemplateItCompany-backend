package ru.itcompany.exeption.status

import ru.itcompany.exeption.NotFoundEntityException


class StatusNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : NotFoundEntityException(message, cause)