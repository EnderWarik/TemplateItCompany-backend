package ru.itcompany.model.dao

import org.ktorm.database.Database
import org.ktorm.dsl.isNull
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import ru.itcompany.model.Appeal
import ru.itcompany.model.Message
import ru.itcompany.model.Status
import ru.itcompany.model.User
import ru.itcompany.model.enum.StatusAppealEnum
import java.time.Instant

val Database.messages get() = this.sequenceOf(MessageDao)
object MessageDao : Table<Message>("messages") {
    val id = long("id").primaryKey().bindTo { it.id }
    val appealId = long("appeal_id").references(AppealDao) { it.appeal }
    val ownerId = long("owner_id").references(UserDao) { it.owner }
    val content = varchar("content").bindTo{ it.content }
    val dateCreate = timestamp("date_create").bindTo { it.dateCreate }
}


