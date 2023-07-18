package ru.itcompany.exeption

import java.util.*

open class UserException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(message,cause)