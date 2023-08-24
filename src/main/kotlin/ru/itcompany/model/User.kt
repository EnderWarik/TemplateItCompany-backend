package ru.itcompany.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import ru.itcompany.model.enum.UserRoleEnum
import java.time.Instant


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
    var isDeleted: Boolean
    val dateCreate: Instant
}

