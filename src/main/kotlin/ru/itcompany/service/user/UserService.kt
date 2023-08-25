package ru.itcompany.service.user

import ru.itcompany.model.Appeal
import ru.itcompany.model.User
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument


interface UserService {
    fun getAll(): List<User>
    fun findByEmail(email: String): User?

    fun create(argument: CreateUserArgument): User

    fun update(id: Long, argument: UpdateUserArgument, email: String): User

    fun delete(id: Long)
}