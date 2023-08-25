package ru.itcompany.exeption.appeal

import ru.itcompany.exeption.NotFoundEntityException


class AppealNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : NotFoundEntityException(message, cause)