package ru.itcompany.repository.appeal

import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.isNull
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.appeal.AppealNotFoundException
import ru.itcompany.exeption.status.StatusNotFoundException
import ru.itcompany.exeption.user.UserNotFoundException
import ru.itcompany.model.Appeal
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.*


class AppealRepositoryImpl(private val database: Database) : AppealRepository {

    override fun getAllBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): List<Appeal> {
        return database.safeTransaction {
            it.appeals.filter(predicate).toList()
        }
    }

    override fun getAll() : List<Appeal>
    {
        return database.safeTransaction {
            it.appeals.toList()
        }
    }

    override fun create(appeal: Appeal): Appeal {
        println(appeal.title)
        database.safeTransaction {
            it.appeals.add(appeal)
        }
        return database.safeTransaction{
            it.appeals.filter {ap ->
                appeal.userEmployeeId?.let {
                    ap.userEmployeeId eq appeal.userEmployeeId!!
                } ?: ap.userEmployeeId.isNull()
                (ap.userCreatorId eq appeal.userCreatorId) and
                        (ap.statusId eq appeal.status.id)
            }.sortedByDescending { it.dateCreate }.first()
        }
    }

    override fun getFirstBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): Appeal {
        return database.safeTransaction {
            it.appeals.filter(predicate).firstOrNull()
        } ?: throw AppealNotFoundException("Appeal not exists")
    }

    override fun update(appeal: Appeal): Appeal {
        appeal.flushChanges()
        return appeal
    }

    override fun delete(appeal: Appeal) {
        appeal.delete()
    }
}




