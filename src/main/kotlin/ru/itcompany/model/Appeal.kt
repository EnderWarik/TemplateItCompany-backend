package ru.itcompany.model

import org.ktorm.entity.Entity
import java.sql.Timestamp


interface Appeal : Entity<Appeal>
{
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