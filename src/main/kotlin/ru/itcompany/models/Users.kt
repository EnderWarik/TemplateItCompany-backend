package ru.itcompany.models

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object Users : Table<Nothing>("users") {
    val id = long("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
}
