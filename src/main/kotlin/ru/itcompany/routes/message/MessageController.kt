package ru.itcompany.routes.message

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.itcompany.routes.appeal.dto.CreateAppealDto
import ru.itcompany.routes.appeal.dto.UpdateAppealDto
import ru.itcompany.routes.appeal.mapper.AppealMapper
import ru.itcompany.routes.message.dto.CreateMessageDto
import ru.itcompany.routes.message.dto.UpdateMessageDto
import ru.itcompany.routes.message.mapper.MessageMapper
import ru.itcompany.routes.status.dto.CreateStatusDto
import ru.itcompany.routes.status.dto.UpdateStatusDto
import ru.itcompany.routes.status.mapper.StatusMapper
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.routes.user.mapper.UserMapper
import ru.itcompany.service.appeal.AppealService
import ru.itcompany.service.appeal.argument.CreateAppealArgument
import ru.itcompany.service.appeal.argument.UpdateAppealArgument
import ru.itcompany.service.message.MessageService
import ru.itcompany.service.message.argument.UpdateMessageArgument
import ru.itcompany.service.status.StatusService
import ru.itcompany.service.user.UserService

fun Route.messageController() {
    val service: MessageService by inject()
    val mapper: MessageMapper by inject()
    val objectMapper: ObjectMapper by inject()
    authenticate("auth-jwt") {
        route("/messages")
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
                    mapper.toCreateMessageArgument(call.receive<CreateMessageDto>())
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
                        mapper.toUpdateMessageArgument(call.receive<UpdateMessageDto>())
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
                    service.delete(id)
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