package ru.itcompany.model.dao

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import ru.itcompany.model.User
import ru.itcompany.model.dao.AppealDao.bindTo
import ru.itcompany.model.enum.UserRoleEnum

val Database.users get() = this.sequenceOf(UserDao)
object UserDao : Table<User>("users") {
    val id = long("id").primaryKey().bindTo { it.id }
    val email = varchar("email").bindTo { it.email }
    val password = varchar("password").bindTo { it.password }
    val role = enum<UserRoleEnum>("role").bindTo { it.role }
    val firstName = varchar("first_name").bindTo { it.firstName }
    val lastName = varchar("last_name").bindTo { it.lastName }
    val thirdName = varchar("third_name").bindTo { it.thirdName }
    val address = varchar("address").bindTo { it.address }
    val phoneNumber = varchar("phone_number").bindTo { it.phoneNumber }
    val inn = varchar("inn").bindTo { it.inn }
    val organizationName = varchar("organization_name").bindTo { it.organizationName }
    val isDeleted = boolean("is_deleted").bindTo { it.isDeleted }
    val dateCreate = timestamp("date_create").bindTo { it.dateCreate }
}
