package ru.itcompany.service.status

import org.ktorm.dsl.eq
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.exeption.user.UserAlreadyExistException
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.StatusDao
import ru.itcompany.model.dao.UserDao
import ru.itcompany.repository.status.StatusRepository
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument


class StatusServiceImpl(private val repository: StatusRepository) : StatusService {
    override fun getAll(): List<Status> {
        return repository.getAll()
    }

    override fun create(argument: CreateStatusArgument): Status {
       return repository.create(Status{
           status = argument.status
           appealId = argument.appealId
       })
    }

    override fun update(id: Long, argument: UpdateStatusArgument): Status {
        val status = repository.getFirstBy{ it: StatusDao ->
            it.id eq id
        }
        status.appealId = argument.appealId
        status.status = argument.status
        return repository.update(status)
    }

    override fun delete(id: Long) {
        val user = repository.getFirstBy{ it: StatusDao ->
            it.id eq id
        }
         repository.delete(user)
    }


}