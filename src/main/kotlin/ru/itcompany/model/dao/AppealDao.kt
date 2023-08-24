package ru.itcompany.model.dao

import org.ktorm.database.Database
import org.ktorm.dsl.isNull
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import ru.itcompany.model.Appeal
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.dao.UserDao.bindTo
import ru.itcompany.model.enum.StatusAppealEnum
import java.time.Instant

val Database.appeals get() = this.sequenceOf(AppealDao)
object AppealDao : Table<Appeal>("appeals") {
    val id = long("id").primaryKey().bindTo { it.id }
    val userCreatorId = long("user_creator_id").references(UserDao) { it.userCreator }
    val userEmployeeId = long("user_employee_id").references(UserDao) { it.userEmployee }
    val statusId = long("status_id").references(StatusDao) { it.status }
    val title = varchar("title").bindTo{ it.title }
    val userDeleteId = long("user_delete_id").references(UserDao) { it.userDelete }
    val deleteReason = varchar("delete_reason").bindTo{ it.deleteReason }
    val isDeleted = boolean("is_deleted").bindTo { it.isDeleted }
    val dateCreate = timestamp("date_create").bindTo { it.dateCreate }
}


