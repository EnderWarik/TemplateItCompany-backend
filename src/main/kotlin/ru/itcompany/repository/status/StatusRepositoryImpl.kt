package ru.itcompany.repository.status

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.status.StatusNotFoundException
import ru.itcompany.exeption.user.UserNotFoundException
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.StatusDao
import ru.itcompany.model.dao.UserDao
import ru.itcompany.model.dao.statuses
import ru.itcompany.model.dao.users


class StatusRepositoryImpl(private val database: Database) : StatusRepository {
    override fun getAllBy(predicate: (StatusDao) -> BinaryExpression<Boolean>): List<Status> {
        return database.safeTransaction {
            it.statuses.filter(predicate).toList()
        }
    }

    override fun getAll() : List<Status>
    {
        return database.safeTransaction {
            it.statuses.toList()
        }
    }

    override fun create(status: Status): Status {
        database.safeTransaction {
            it.statuses.add(status)
        }
        return database.safeTransaction{
            it.statuses.filter { it.appealId eq status.appealId }.sortedByDescending { it.dateCreate }.first()
        }
    }

    override fun getFirstBy(predicate: (StatusDao) -> BinaryExpression<Boolean>): Status {
        return database.safeTransaction {
            it.statuses.filter(predicate).firstOrNull()
        } ?: throw StatusNotFoundException("Not exists")
    }

    override fun update(status: Status): Status {
        status.flushChanges()
        return status
    }

    override fun delete(status: Status) {
        status.delete()
    }


}




