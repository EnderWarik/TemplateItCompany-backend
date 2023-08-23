package ru.itcompany.repository.appeal

import org.ktorm.expression.BinaryExpression
import ru.itcompany.model.Appeal
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.AppealDao
import ru.itcompany.model.dao.StatusDao
import ru.itcompany.model.dao.UserDao

interface AppealRepository {
    fun getAllBy(predicate: (AppealDao) -> BinaryExpression<Boolean>) :List<Appeal>

    fun getAll() :List<Appeal>

    fun create(appeal: Appeal): Appeal

    fun getFirstBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): Appeal

    fun getFirstOrNullBy(predicate: (AppealDao) -> BinaryExpression<Boolean>): Appeal?

    fun update(appeal: Appeal): Appeal

    fun delete(appeal: Appeal)

}