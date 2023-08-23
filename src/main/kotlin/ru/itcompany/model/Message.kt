package ru.itcompany.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import org.ktorm.schema.TimestampSqlType
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
import java.security.acl.Owner
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date


interface Message : Entity<Message> {
    companion object : Entity.Factory<Message>()
    val id: Long
    var appeal: Appeal
    var owner: User
    var content: String
    var dateCreate: Instant


}

