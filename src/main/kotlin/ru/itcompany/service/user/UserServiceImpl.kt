package ru.itcompany.service.user

import org.ktorm.dsl.eq
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.exeption.UserAlreadyExistException
import ru.itcompany.model.User
import ru.itcompany.model.dao.Users
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument


class UserServiceImpl(private val repository: UserRepository) : UserService {
    override fun getAll(): List<User> {
        return repository.getAll()
    }

    override fun create(argument: CreateUserArgument): User {
        repository.findByEmail(argument.email)?.let { throw UserAlreadyExistException("User already exist") }
        return repository.create(User{
            email = argument.email
            password = BCrypt.hashpw(argument.password, BCrypt.gensalt())
            role = argument.role
            firstName = argument.firstName
            lastName = argument.lastName
            thirdName = argument.thirdName
            address = argument.address
            phoneNumber = argument.phoneNumber
            inn = argument.inn
            organizationName = argument.organizationName
        })
    }

    override fun update(id: Long, argument: UpdateUserArgument): User {
        val user = repository.getFirstBy{ it: Users ->
            it.id eq id
        }
        user.email = argument.email
        user.password = argument.password
        user.role = argument.role
        user.firstName = argument.firstName
        user.lastName = argument.lastName
        user.thirdName = argument.thirdName
        user.address = argument.address
        user.phoneNumber = argument.phoneNumber
        user.inn = argument.inn
        user.organizationName = argument.organizationName

        return repository.update(user)
    }

    override fun delete(id: Long) {
        val user = repository.getFirstBy{ it: Users ->
            it.id eq id
        }
         repository.delete(user)
    }


}