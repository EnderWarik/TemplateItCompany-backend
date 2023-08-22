package ru.itcompany.repository.user

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.UserNotFoundException
import ru.itcompany.model.User
import ru.itcompany.model.dao.UserDao
import ru.itcompany.model.dao.users


class UserRepositoryImpl(private val database: Database) : UserRepository {

    override fun getAll() : List<User>
    {
        return database.safeTransaction {
            it.users.toList()
        }
    }
    override fun getAllBy(predicate: (UserDao) -> BinaryExpression<Boolean>) : List<User>
    {
        return database.safeTransaction {
            it.users.filter(predicate).toList()
        }
    }
    override fun findByEmail(email:String):User?  {
        return database.safeTransaction {
            it.users.filter { UserDao.email eq email}.firstOrNull()
        }
    }

    override fun create(user: User): User {
        database.safeTransaction {
             it.users.add(user)
         }
        return getFirstBy { it: UserDao ->
            it.email eq user.email
        }
    }

    override fun getFirstBy(predicate: (UserDao) -> BinaryExpression<Boolean>): User {
        return database.safeTransaction {
            it.users.filter(predicate).firstOrNull()
        } ?: throw UserNotFoundException("Not exists")
    }

    override fun update(user: User): User {
        user.flushChanges()
        return user
    }

    override fun delete(user: User) {
        user.delete()
    }

}




