package ru.itcompany.repository

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.DatabaseFactory
import ru.itcompany.db.safeTransaction
import ru.itcompany.models.User
import ru.itcompany.models.Users
import ru.itcompany.models.users


class UserRepository {

    private val database: Database = DatabaseFactory.getDataBase()

    fun getAll(predicate: BinaryExpression<Boolean>) :List<User>?
    {
        return database.safeTransaction {
            it.users.filter {predicate}.toList()
        }
    }

    fun findByEmail(email:String):User?  {
        return database.safeTransaction {
            it.users.filter {Users.email eq email}.firstOrNull()
        }
    }

    fun create(user: User) {
        database.safeTransaction {
             it.users.add(user)
         }
    }

}


