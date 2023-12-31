package ru.itcompany.service.user

import ru.itcompany.model.User
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument


interface UserService
{
    fun getAll(): List<User>

    fun findByEmail(email: String): User?

    fun getFromTo(offset: Int, limit: Int): PaginationResponse<User>

    fun create(argument: CreateUserArgument): User

    fun update(id: Long, argument: UpdateUserArgument, updaterEmail: String): User

    fun delete(id: Long)
}