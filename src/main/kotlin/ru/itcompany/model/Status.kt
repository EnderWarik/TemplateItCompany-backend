package ru.itcompany.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import org.ktorm.schema.TimestampSqlType
import ru.itcompany.model.enum.StatusAppealEnum
import ru.itcompany.model.enum.UserRoleEnum
import java.sql.Timestamp
import java.time.Instant


interface Status : Entity<Status> {

    companion object : Entity.Factory<Status>()
    val id: Long
    var status: StatusAppealEnum
    var appealId: String
    var dateCreate: Instant


}

