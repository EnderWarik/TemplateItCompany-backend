package ru.itcompany.service

import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import ru.itcompany.DatabaseFactory
import ru.itcompany.models.Users


class UserService {

    private val database: Database = DatabaseFactory.getDataBase()

    fun main(){
        for (row in database.from(Users).select()) {
            println(row[Users.name])
        }
    }
}