package ru.itcompany.repository.status

import org.ktorm.database.Database
import org.ktorm.dsl.*
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
import ru.itcompany.model.enum.StatusAppealEnum
import java.sql.Timestamp


class StatusRepositoryImpl(private val database: Database) : StatusRepository {
    override fun getAllBy(predicate: (StatusDao) -> BinaryExpression<Boolean>): List<Status> {
        return database.safeTransaction {
            it.statuses.filter(predicate).filter { it.isDeleted eq false }.toList()
        }
    }

    override fun getAll() : List<Status>
    {
        return database.safeTransaction {
            it.statuses.filter { it.isDeleted eq false }.toList()
        }
    }
    override fun getFromTo(offset: Int, limit: Int): List<Status> {
        return database.safeTransaction {
            it.statuses.drop(offset).take(limit).toList()
        }
    }

    override fun totalRecords(): Int {
        return  database.safeTransaction {
            it.statuses.totalRecordsInAllPages
        }
    }
    override fun create(status: Status): Status {
        database.safeTransaction {
            it.statuses.add(status)
        }
        return database.safeTransaction{
            it.statuses
                .filter { it.appealId eq status.appealId }
                .filter { it.isDeleted eq false }
                .sortedByDescending { it.dateCreate }
                .first()
        }
    }

    override fun getFirstBy(predicate: (StatusDao) -> BinaryExpression<Boolean>): Status {
        return database.safeTransaction {
            it.statuses.filter(predicate).filter { it.isDeleted eq false }.firstOrNull()
        } ?: throw StatusNotFoundException("Status not exists")
    }

    override fun update(status: Status): Status {
        status.flushChanges()
        return status
    }

    override fun delete(status: Status) {
        status.isDeleted = true
        status.flushChanges()
    }


}




