package ru.itcompany.model

import org.ktorm.entity.Entity
import ru.itcompany.model.enum.UserRoleEnum
import java.sql.Timestamp


interface User : Entity<User>
{

    companion object : Entity.Factory<User>()

    var id: Long
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
    var isDeleted: Boolean
    var dateCreate: Timestamp
}

