package ru.itcompany.model

import org.ktorm.entity.Entity
import ru.itcompany.model.enum.StatusAppealEnum
import java.sql.Timestamp


interface Status : Entity<Status>
{

    companion object : Entity.Factory<Status>()

    var id: Long
    var status: StatusAppealEnum
    var appealId: Long
    var isDeleted: Boolean
    var dateCreate: Timestamp


}

