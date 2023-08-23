package ru.itcompany.service.message

import org.ktorm.dsl.eq
import org.ktorm.dsl.isNull
import ru.itcompany.model.Appeal
import ru.itcompany.model.Message
import ru.itcompany.model.User
import ru.itcompany.model.dao.AppealDao
import ru.itcompany.repository.appeal.AppealRepository
import ru.itcompany.repository.message.MessageRepository
import ru.itcompany.repository.status.StatusRepository
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.message.argument.CreateMessageArgument
import ru.itcompany.service.message.argument.UpdateMessageArgument


class MessageServiceImpl(
    private val appealRepository: AppealRepository,
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository
) : MessageService {
    override fun getAll(): List<Message> {
        return messageRepository.getAll()
    }

    override fun create(argument: CreateMessageArgument): Message {
        val appeal = appealRepository.getFirstBy { it.id eq argument.appealId }
        val owner = userRepository.getFirstBy { it.id eq argument.ownerId }
        return messageRepository.create(Message{
            this.appeal = appeal
            this.owner = owner
            content = argument.content
        })
    }

    override fun update(id: Long, argument: UpdateMessageArgument): Message {
        val message = messageRepository.getFirstBy { it.id eq id }
        val appeal = appealRepository.getFirstBy { it.id eq argument.appealId }
        val owner = userRepository.getFirstBy { it.id eq argument.ownerId }

        message.owner = owner
        message.appeal = appeal
        message.content = argument.content
        return  messageRepository.update(message)
    }

    override fun delete(id: Long) {
        val message = messageRepository.getFirstBy { it.id eq id }
        messageRepository.delete(message)
    }


}