package ru.itcompany.repository.appeal

import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.isNull
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.appeal.AppealNotFoundException
import ru.itcompany.model.Appeal
import ru.itcompany.model.dao.AppealDao
import ru.itcompany.model.dao.appeals

class AppealRepositoryImpl(private val database: Database) : AppealRepository
{

    override fun getAllBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): List<Appeal>
    {
        return database.safeTransaction {
            it.appeals.filter(predicate).filter { it.isDeleted eq false }.toList()
        }
    }

    override fun getAll(): List<Appeal>
    {
        return database.safeTransaction {
            it.appeals.filter { it.isDeleted eq false }.map {
                Appeal {
                    id = it.id
                    userCreator = it.userCreator
                    this.status = it.status
                    userEmployee = it.userEmployee
                    title = it.title
                    userDelete = it.userDelete
                    deleteReason = it.deleteReason
                    isDeleted = it.isDeleted
                    dateCreate = it.dateCreate
                }
            }.toList()
        }
    }

    override fun getFromTo(offset: Int, limit: Int): List<Appeal>
    {
        return database.safeTransaction {
            it.appeals.drop(offset).take(limit).toList()
        }
    }

    override fun totalRecords(): Int
    {
        return database.safeTransaction {
            it.appeals.totalRecordsInAllPages
        }
    }

    override fun create(appeal: Appeal): Appeal
    {
        database.safeTransaction {
            it.appeals.add(appeal)
        }
        return database.safeTransaction {
            it.appeals.filter { ap ->
                appeal.userEmployee?.let {
                    ap.userEmployeeId eq appeal.userEmployee!!.id
                } ?: ap.userEmployeeId.isNull()
                (ap.userCreatorId eq appeal.userCreator.id) and
                        (ap.statusId eq appeal.status.id)
            }.sortedByDescending { it.dateCreate }
                .filter { it.isDeleted eq false }
                .first()
        }
    }

    override fun getFirstBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): Appeal
    {
        return database.safeTransaction {
            it.appeals.filter(predicate).filter { it.isDeleted eq false }.firstOrNull()
        } ?: throw AppealNotFoundException("Appeal not exists")
    }

    override fun getFirstOrNullBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): Appeal?
    {
        return database.safeTransaction {
            it.appeals.filter(predicate).filter { it.isDeleted eq false }.firstOrNull()
        }
    }

    override fun update(appeal: Appeal): Appeal
    {
        appeal.flushChanges()
        return appeal
    }

    override fun delete(appeal: Appeal)
    {
        appeal.isDeleted = true
        appeal.flushChanges()
    }
}




