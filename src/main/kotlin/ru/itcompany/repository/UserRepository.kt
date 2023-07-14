package ru.itcompany.repository

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.filter
import org.ktorm.entity.first
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.take
import org.ktorm.expression.SelectExpression
import org.ktorm.expression.UnionExpression
import org.ktorm.schema.ColumnDeclaring
import ru.itcompany.db.DatabaseFactory
import ru.itcompany.exeption.UserNotFoundException
import ru.itcompany.models.User
import ru.itcompany.models.Users
import ru.itcompany.models.users


class UserRepository {
    private val database: Database = DatabaseFactory.getDataBase()

    fun getAll() :List<User> {
        return database.from(Users).select().map { row ->  Users.createEntity(row) }
    }

    fun findByEmail(email:String,password : String):User?  {
        return database.users.filter {(Users.email eq email) and (Users.password eq password)}.firstOrNull()
    }

    fun isExist(email:String,password : String):Boolean  {
        return findByEmail(email,password)!== null
    }
}


