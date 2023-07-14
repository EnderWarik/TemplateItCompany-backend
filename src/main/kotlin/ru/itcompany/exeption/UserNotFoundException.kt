package ru.itcompany.exeption

import java.util.*

class UserNotFoundException(
    private val errorMessage: String
) : RuntimeException(errorMessage)