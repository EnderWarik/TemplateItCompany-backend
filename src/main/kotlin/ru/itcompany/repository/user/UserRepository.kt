package ru.itcompany.repository.user

import org.ktorm.expression.BinaryExpression
import ru.itcompany.model.User
import ru.itcompany.model.dao.UserDao

interface UserRepository {
    fun getAllBy(predicate: (UserDao) -> BinaryExpression<Boolean>): List<User>

    fun getAll() :List<User>

    fun findByEmail(email:String):User?

    fun getFromTo(offset: Int, limit: Int): List<User>

    fun create(user: User): User

    fun getFirstBy(predicate: (UserDao) -> BinaryExpression<Boolean>): User

    fun getFirstOrNullBy(predicate: (UserDao) -> BinaryExpression<Boolean>): User?

    fun update(user: User): User

    fun delete(user: User)

}