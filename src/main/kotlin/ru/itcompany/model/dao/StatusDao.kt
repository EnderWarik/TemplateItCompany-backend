package ru.itcompany.model.dao

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import ru.itcompany.model.Status
import ru.itcompany.model.enum.StatusAppealEnum

val Database.statuses get() = this.sequenceOf(StatusDao)

object StatusDao : Table<Status>("statuses")
{
    val id = long("id").primaryKey().bindTo { it.id }
    val status = enum<StatusAppealEnum>("status").bindTo { it.status }
    val appealId = long("appeal_id").bindTo { it.appealId }
    val isDeleted = boolean("is_deleted").bindTo { it.isDeleted }
    val dateCreate = jdbcTimestamp("date_create").bindTo { it.dateCreate }
}
