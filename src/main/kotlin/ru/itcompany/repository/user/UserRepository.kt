package ru.itcompany.repository.user

import org.ktorm.expression.BinaryExpression
import org.ktorm.schema.ColumnDeclaring
import ru.itcompany.model.User
import ru.itcompany.model.dao.Users

interface UserRepository {
    fun getAllBy(predicate: (Users) -> BinaryExpression<Boolean>) :List<User>

    fun getAll() :List<User>

    fun findByEmail(email:String):User?

    fun create(user: User): User

    fun getFirstBy(predicate: (Users) -> BinaryExpression<Boolean>): User

    fun update(user: User): User

    fun delete(user: User)

}