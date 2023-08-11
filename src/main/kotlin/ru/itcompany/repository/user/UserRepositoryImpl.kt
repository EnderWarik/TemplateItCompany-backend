package ru.itcompany.repository.user

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.models.User
import ru.itcompany.models.dao.Users
import ru.itcompany.models.dao.users


class UserRepositoryImpl(private val database: Database) : UserRepository {


    override fun getAll(predicate: BinaryExpression<Boolean>) :List<User>?
    {
        return database.safeTransaction {
            it.users.filter {predicate}.toList()
        }
    }

    override fun findByEmail(email:String):User?  {
        return database.safeTransaction {
            it.users.filter { Users.email eq email}.firstOrNull()
        }
    }

    override fun create(user: User) {
        database.safeTransaction {
             it.users.add(user)
         }
    }

}


