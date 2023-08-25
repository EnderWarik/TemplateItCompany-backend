package ru.itcompany.repository.message

import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.message.MessageNotFoundException
import ru.itcompany.model.Message
import ru.itcompany.model.dao.MessageDao
import ru.itcompany.model.dao.messages


class MessageRepositoryImpl(private val database: Database) : MessageRepository
{

    override fun getAllBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): List<Message>
    {
        return database.safeTransaction {
            it.messages.filter(predicate).filter { it.isDeleted eq false }.toList()
        }
    }

    override fun getAll(): List<Message>
    {
        return database.safeTransaction {
            it.messages.filter { it.isDeleted eq false }.toList()
        }
    }

    override fun getFromTo(offset: Int, limit: Int): List<Message>
    {
        return database.safeTransaction {
            it.messages.drop(offset).take(limit).toList()
        }
    }

    override fun totalRecords(): Int
    {
        return database.safeTransaction {
            it.messages.totalRecordsInAllPages
        }
    }

    override fun create(message: Message): Message
    {
        database.safeTransaction {
            it.messages.add(message)
        }
        return getFirstBy { ms ->
            (ms.appealId eq message.appeal.id) and
                    (ms.ownerId eq message.owner.id) and
                    (ms.content eq message.content)
        }
    }

    override fun getFirstBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): Message
    {
        return database.safeTransaction {
            it.messages.filter(predicate).filter { it.isDeleted eq false }.firstOrNull()
        } ?: throw MessageNotFoundException("Message not exists")
    }

    override fun update(message: Message): Message
    {
        message.flushChanges()
        return message
    }

    override fun delete(message: Message)
    {
        message.isDeleted = true
        message.flushChanges()
    }

}




