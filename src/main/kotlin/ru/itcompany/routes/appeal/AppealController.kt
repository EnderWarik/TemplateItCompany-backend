package ru.itcompany.routes.appeal

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.itcompany.routes.appeal.dto.CreateAppealDto
import ru.itcompany.routes.appeal.dto.DeleteAppealDto
import ru.itcompany.routes.appeal.dto.UpdateAppealDto
import ru.itcompany.routes.appeal.mapper.AppealMapper
import ru.itcompany.routes.status.dto.CreateStatusDto
import ru.itcompany.routes.status.dto.UpdateStatusDto
import ru.itcompany.routes.status.mapper.StatusMapper
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.routes.user.mapper.UserMapper
import ru.itcompany.service.appeal.AppealService
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.status.StatusService
import ru.itcompany.service.user.UserService

fun Route.appealController() {
    val service: AppealService by inject()
    val mapper: AppealMapper by inject()
    val objectMapper: ObjectMapper by inject()
    authenticate("auth-jwt") {
        route("/appeals")
        {
            get()
            {
                service.getAll().let {
                    call.respond(objectMapper.writeValueAsString(it))
                }
            }
            post("/create")
            {

                service.create(
                    mapper.toCreateAppealArgument(call.receive<CreateAppealDto>())
                ).let {
                    call.respond(objectMapper.writeValueAsString(it))
                }

            }
            put("/update/{id}")
            {
                val id = call.parameters["id"]?.toLong()
                if(id != null)
                {
                    service.update(
                        id,
                        mapper.toUpdateStatusArgument(call.receive<UpdateAppealDto>())
                    ).let {
                        call.respond(objectMapper.writeValueAsString(it))
                    }
                }
                else
                {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            delete("/delete/{id}")
            {
                val id = call.parameters["id"]?.toLong()
                if(id != null)
                {
                    service.delete(id,
                        mapper.toDeleteAppealArgument(call.receive<DeleteAppealDto>())
                    )
                    call.respond(HttpStatusCode.OK)
                }
                else
                {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}