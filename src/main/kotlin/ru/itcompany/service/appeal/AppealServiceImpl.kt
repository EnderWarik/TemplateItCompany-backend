package ru.itcompany.service.appeal

import org.ktorm.dsl.eq
import ru.itcompany.model.Appeal
import ru.itcompany.model.dao.AppealDao
import ru.itcompany.repository.appeal.AppealRepository
import ru.itcompany.repository.status.StatusRepository
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument


class AppealServiceImpl(
    private val appealRepository: AppealRepository,
    private val statusRepository: StatusRepository
) : AppealService {
    override fun getAll(): List<Appeal> {
        return appealRepository.getAll()
    }

    override fun create(argument: CreateAppealArgument): Appeal {

        val status = statusRepository.getFirstBy { it.id eq argument.statusId }
        return appealRepository.create(Appeal{
            userCreatorId = argument.userCreatorId
            this.status = status
            userEmployeeId = argument.userEmployeeId
            title = argument.title
        })
    }

    override fun update(id: Long, argument: UpdateAppealArgument): Appeal {
        val appeal = appealRepository.getFirstBy{ it: AppealDao ->
            it.id eq id
        }
        val status = statusRepository.getFirstBy { it.id eq argument.statusId }
        appeal.userCreatorId = argument.userCreatorId
        appeal.status = status
        appeal.userEmployeeId = argument.userEmployeeId
        appeal.title = argument.title
        return appealRepository.update(appeal)
    }
    override fun delete(id: Long) {
        val appeal = appealRepository.getFirstBy{ it: AppealDao ->
            it.id eq id
        }
         appealRepository.delete(appeal)
    }


}