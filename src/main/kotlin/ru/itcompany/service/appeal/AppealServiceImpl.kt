package ru.itcompany.service.appeal

import org.ktorm.dsl.eq
import org.ktorm.dsl.isNull
import ru.itcompany.model.Appeal
import ru.itcompany.model.User
import ru.itcompany.model.dao.AppealDao
import ru.itcompany.repository.appeal.AppealRepository
import ru.itcompany.repository.status.StatusRepository
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.DeleteAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument


class AppealServiceImpl(
    private val appealRepository: AppealRepository,
    private val statusRepository: StatusRepository,
    private val userRepository: UserRepository
) : AppealService {
    override fun getAll(): List<Appeal> {
        return appealRepository.getAll()
    }

    override fun findById(id: Long): Appeal? {
        return appealRepository.getFirstOrNullBy{ it.id eq id }
    }

    override fun create(argument: CreateAppealArgument): Appeal {

        val status = statusRepository.getFirstBy { it.id eq argument.statusId }
        val user = userRepository.getFirstBy { it.id eq argument.userCreatorId }
        var employee: User? = null
        if(argument.userEmployeeId != null) {
           employee = userRepository.getFirstBy { it.id eq argument.userEmployeeId!! }
        }

        return appealRepository.create(Appeal{
            userCreator = user
            this.status = status
            userEmployee = employee
            title = argument.title
            userDelete = null
            deleteReason = null
        })
    }

    override fun update(id: Long, argument: UpdateAppealArgument): Appeal {
        val appeal = appealRepository.getFirstBy{ it: AppealDao ->
            it.id eq id
        }
        val status = statusRepository.getFirstBy { it.id eq argument.statusId }
        val user = userRepository.getFirstBy { it.id eq argument.userCreatorId }
        var employee: User? = null
        if(argument.userEmployeeId != null) {
            employee = userRepository.getFirstBy { it.id eq argument.userEmployeeId!! }
        }
        appeal.userCreator = user
        appeal.status = status
        appeal.userEmployee = employee
        appeal.title = argument.title
        return appealRepository.update(appeal)
    }
    override fun delete(id: Long, argument: DeleteAppealArgument) {
        val appeal = appealRepository.getFirstBy{ it: AppealDao ->
            it.id eq id
        }
        val user = userRepository.getFirstBy { it.id eq argument.userDeleteId }
        appeal.deleteReason = argument.deleteReason
        appeal.userDelete = user
         appealRepository.delete(appeal)
    }


}