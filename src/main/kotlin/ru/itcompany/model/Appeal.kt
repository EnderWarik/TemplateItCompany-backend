package ru.itcompany.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import org.ktorm.schema.TimestampSqlType
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import kotlin.reflect.KClass


interface Appeal : Entity<Appeal> {
    companion object : Entity.Factory<Appeal>()
    var id: Long
    var userCreator: User
    var userEmployee: User?
    var status: Status
    var title: String
    var userDelete: User?
    var deleteReason: String?
    var isDeleted: Boolean
    var dateCreate: Timestamp
}