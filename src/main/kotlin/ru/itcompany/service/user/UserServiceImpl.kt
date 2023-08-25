package ru.itcompany.service.user

import org.ktorm.dsl.eq
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.exeption.user.UserAlreadyExistException
import ru.itcompany.exeption.user.UserNotAccessException
import ru.itcompany.model.User
import ru.itcompany.model.dao.UserDao
import ru.itcompany.model.enum.UserRoleEnum
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument
import ru.itcompany.service.user.response.UsersResponse


class UserServiceImpl(private val repository: UserRepository) : UserService {
    override fun getAll(): List<User> {
        return repository.getAll()
    }

    override fun findByEmail(email: String): User? {
        return repository.getFirstOrNullBy{it.email eq email}
    }

    override fun getFromTo(offset: Int, limit: Int): UsersResponse {
        return UsersResponse(
            values = repository.getFromTo(offset,limit),
            totalRecords = repository.totalRecords()
        )

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
            isDeleted = false
        })
    }

    override fun update(id: Long, argument: UpdateUserArgument, email: String): User {
        val user = repository.getFirstBy{ it: UserDao ->
            it.id eq id
        }
        if(user.email !== email && user.role !== UserRoleEnum.Admin)
        {
            throw UserNotAccessException("User do not have access")
        }
        if(user.email != argument.email)
        {
            findByEmail(argument.email)?.let {
                throw UserAlreadyExistException("This email is busy")
            }
        }

        user.email = argument.email
        user.password = BCrypt.hashpw(argument.password, BCrypt.gensalt())
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
        val user = repository.getFirstBy{ it: UserDao ->
            it.id eq id
        }
         repository.delete(user)
    }


}