package ru.itcompany.exeption

class DataBaseExeption(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(message,cause)