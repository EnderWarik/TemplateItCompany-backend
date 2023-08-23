package ru.itcompany.repository.message

import org.ktorm.expression.BinaryExpression
import ru.itcompany.model.Appeal
import ru.itcompany.model.Message
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.AppealDao
import ru.itcompany.model.dao.MessageDao
import ru.itcompany.model.dao.StatusDao
import ru.itcompany.model.dao.UserDao

interface MessageRepository {
    fun getAllBy(predicate: (MessageDao) -> BinaryExpression<Boolean>) :List<Message>

    fun getAll() :List<Message>

    fun create(message: Message): Message

    fun getFirstBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): Message

    fun update(message: Message): Message

    fun delete(message: Message)

}