package ru.itcompany.repository.status

import org.ktorm.expression.BinaryExpression
import ru.itcompany.model.Status
import ru.itcompany.model.dao.StatusDao

interface StatusRepository
{
    fun getAllBy(predicate: (StatusDao) -> BinaryExpression<Boolean>): List<Status>

    fun getAll(): List<Status>

    fun getFromTo(offset: Int, limit: Int): List<Status>

    fun totalRecords(): Int

    fun create(status: Status): Status

    fun getFirstBy(predicate: (StatusDao) -> BinaryExpression<Boolean>): Status

    fun update(status: Status): Status

    fun delete(status: Status)

}