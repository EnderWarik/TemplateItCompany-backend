package ru.itcompany.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.enum
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import ru.itcompany.models.enum.UserRoleEnum

interface  User : Entity<User> {
    val id: Long
    val email: String
    val password: String
    val role: UserRoleEnum
    val firstName: String
    val lastName: String
    val thirdName: String?
    val address: String?
    val phoneNumber: String?
    val inn: String?
    val organizationName: String?
}
val Database.users get() = this.sequenceOf(Users)
object Users : Table<User>("users") {
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
}