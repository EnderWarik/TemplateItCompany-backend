package ru.itcompany.service.status

import org.ktorm.dsl.eq
import ru.itcompany.model.Meta
import ru.itcompany.model.Status
import ru.itcompany.model.dao.StatusDao
import ru.itcompany.repository.status.StatusRepository
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.status.argument.CreateStatusArgument
import ru.itcompany.service.status.argument.UpdateStatusArgument


class StatusServiceImpl(private val repository: StatusRepository) : StatusService
{
    override fun getAll(): List<Status>
    {
        return repository.getAll()
    }

    override fun create(argument: CreateStatusArgument): Status
    {
        return repository.create(Status {
            status = argument.status
            appealId = argument.appealId
            isDeleted = false
        })
    }

    override fun getFromTo(offset: Int, limit: Int): PaginationResponse<Status>
    {
        val meta = Meta(
            totalCounts = repository.totalRecords(),
            limit = limit,
            offset = offset
        )
        return PaginationResponse(
            data = repository.getFromTo(offset, limit),
            meta = meta
        )

    }

    override fun update(id: Long, argument: UpdateStatusArgument): Status
    {
        val status = repository.getFirstBy { it: StatusDao ->
            it.id eq id
        }
        status.appealId = argument.appealId
        status.status = argument.status
        return repository.update(status)
    }

    override fun delete(id: Long)
    {
        val user = repository.getFirstBy { it: StatusDao ->
            it.id eq id
        }
        repository.delete(user)
    }


}