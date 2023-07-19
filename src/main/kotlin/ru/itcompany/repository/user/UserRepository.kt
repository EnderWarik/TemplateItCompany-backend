package ru.itcompany.repository.user

import org.ktorm.expression.BinaryExpression
import ru.itcompany.models.User

interface UserRepository {
    fun getAll(predicate: BinaryExpression<Boolean>) :List<User>?
    fun findByEmail(email:String):User?
    fun create(user: User)
}