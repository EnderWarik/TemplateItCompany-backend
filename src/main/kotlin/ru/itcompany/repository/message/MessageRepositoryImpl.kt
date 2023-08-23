package ru.itcompany.repository.message

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.isNull
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.appeal.AppealNotFoundException
import ru.itcompany.exeption.message.MessageNotFoundException
import ru.itcompany.exeption.status.StatusNotFoundException
import ru.itcompany.exeption.user.UserNotFoundException
import ru.itcompany.model.Appeal
import ru.itcompany.model.Message
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.*


class MessageRepositoryImpl(private val database: Database) : MessageRepository {

    override fun getAllBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): List<Message> {
        return database.safeTransaction {
            it.messages.filter(predicate).toList()
        }
    }

    override fun getAll() : List<Message>
    {
        return database.safeTransaction {
            it.messages.toList()
        }
    }

    override fun create(message: Message): Message {
        database.safeTransaction {
            it.messages.add(message)
        }
        return database.safeTransaction{
            it.messages.filter {ms ->
                (ms.appealId eq message.appeal.id) and
                        (ms.ownerId eq message.owner.id) and
                        (ms.content eq message.content)
            }.first()
        }
    }

    override fun getFirstBy(predicate: (MessageDao) -> BinaryExpression<Boolean>): Message {
        return database.safeTransaction {
            it.messages.filter(predicate).firstOrNull()
        } ?: throw MessageNotFoundException("Message not exists")
    }

    override fun update(message: Message): Message {
        message.flushChanges()
        return message
    }

    override fun delete(message: Message) {
        message.delete()
    }

}




