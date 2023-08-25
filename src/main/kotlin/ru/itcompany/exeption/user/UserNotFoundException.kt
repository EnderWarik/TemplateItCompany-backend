package ru.itcompany.exeption.user

import ru.itcompany.exeption.NotFoundEntityException


class UserNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : NotFoundEntityException(message, cause)