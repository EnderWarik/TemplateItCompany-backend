package ru.itcompany.exeption.user

import java.util.*

open class UserException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message,cause)