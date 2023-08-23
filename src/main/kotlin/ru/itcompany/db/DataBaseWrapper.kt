package ru.itcompany.db

import org.ktorm.database.Database
import org.postgresql.util.PSQLException
import ru.itcompany.exeption.DataBaseException

    fun <T> Database.safeTransaction(statement: (Database) -> T): T {
        return try {
            this.useTransaction { statement(this) }
        } catch (e: PSQLException) {
            throw DataBaseException(e.localizedMessage)
        }
    }
