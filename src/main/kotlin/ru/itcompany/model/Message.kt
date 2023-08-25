package ru.itcompany.model

import org.ktorm.entity.Entity
import java.sql.Timestamp


interface Message : Entity<Message>
{
    companion object : Entity.Factory<Message>()

    var id: Long
    var appeal: Appeal
    var owner: User
    var content: String
    var isDeleted: Boolean
    var dateCreate: Timestamp


}

