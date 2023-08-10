package ru.itcompany.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.enum
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import ru.itcompany.models.enum.UserRoleEnum
import ru.itcompany.service.user.argument.RegisterUserArgument


interface  User : Entity<User> {
    companion object : Entity.Factory<User>()
    val id: Long
    var email: String
    var password: String
    var role: UserRoleEnum
    var firstName: String
    var lastName: String
    var thirdName: String?
    var address: String?
    var phoneNumber: String?
    var inn: String?
    var organizationName: String?

}

