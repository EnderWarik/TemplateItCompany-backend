package ru.itcompany.repository.user

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.ktorm.expression.BinaryExpression
import ru.itcompany.db.safeTransaction
import ru.itcompany.exeption.user.UserNotFoundException
import ru.itcompany.model.User
import ru.itcompany.model.dao.UserDao
import ru.itcompany.model.dao.users
import ru.itcompany.model.enum.UserRoleEnum
import java.sql.Timestamp


class UserRepositoryImpl(private val database: Database) : UserRepository {

    override fun getAll() : List<User>
    {
        return database.safeTransaction {
            it.users.filter { it.isDeleted eq false }.toList()
        }
    }
    override fun getAllBy(predicate: (UserDao) -> BinaryExpression<Boolean>) : List<User>
    {
        return database.safeTransaction {
            it.users.filter(predicate).filter { it.isDeleted eq false }.toList()
        }
    }
    override fun findByEmail(email:String):User?  {
        return database.safeTransaction {
            it.users.filter { it.email eq email}.filter { it.isDeleted eq false }.firstOrNull()
        }
    }

    override fun getFromTo(offset: Int, limit: Int): List<User> {
        return database.safeTransaction {
            it.from(UserDao).select().where(UserDao.isDeleted eq false).offset(offset).limit(limit).map {
                User{
                     id = it[UserDao.id]!!
                     email = it[UserDao.email]!!
                     password = it[UserDao.password]!!
                     role = it[UserDao.role]!!
                     firstName = it[UserDao.firstName]!!
                     lastName = it[UserDao.lastName]!!
                     thirdName = it[UserDao.thirdName]!!
                     address = it[UserDao.address]!!
                     phoneNumber = it[UserDao.phoneNumber]!!
                     inn = it[UserDao.inn]!!
                     organizationName = it[UserDao.organizationName]!!
                     isDeleted = it[UserDao.isDeleted]!!
                     dateCreate = it[UserDao.dateCreate]!!
                }

            }
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
            it.users.filter(predicate).filter { it.isDeleted eq false }.firstOrNull()
        } ?: throw UserNotFoundException("User not exists")
    }
    override fun getFirstOrNullBy(predicate: (UserDao) -> BinaryExpression<Boolean>): User? {
        return database.safeTransaction {
            it.users.filter(predicate).filter { it.isDeleted eq false }.firstOrNull()
        }
    }
    override fun update(user: User): User {
        user.flushChanges()
        return user
    }

    override fun delete(user: User) {
        user.isDeleted = true
        user.flushChanges()
    }

}




