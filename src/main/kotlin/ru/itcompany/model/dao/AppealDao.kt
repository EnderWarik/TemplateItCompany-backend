package ru.itcompany.model.dao

import org.ktorm.database.Database
import org.ktorm.dsl.isNull
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import ru.itcompany.model.Appeal
import ru.itcompany.model.Status
import ru.itcompany.model.enum.StatusAppealEnum
import java.time.Instant

val Database.appeals get() = this.sequenceOf(AppealDao)
object AppealDao : Table<Appeal>("appeals") {
    val id = long("id").primaryKey().bindTo { it.id }
    val userCreatorId = long("user_creator_id").bindTo { it.userCreatorId }
    val userEmployeeId = long("user_employee_id").bindTo { it.userEmployeeId }
    val statusId = long("status_id").references(StatusDao) { it.status }
    val title = varchar("title").bindTo{ it.title }
    val dateCreate = timestamp("date_create").bindTo { it.dateCreate }
}


