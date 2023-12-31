package ru.itcompany.repository.message

import org.ktorm.expression.BinaryExpression
import ru.itcompany.model.Message
import ru.itcompany.model.dao.MessageDao

interface MessageRepository
{
    fun getAllBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): List<Message>

    fun getAll(): List<Message>

    fun getFromTo(offset: Int, limit: Int): List<Message>

    fun totalRecords(): Int
    fun create(message: Message): Message

    fun getFirstBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): Message

    fun update(message: Message): Message

    fun delete(message: Message)

}