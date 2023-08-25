package ru.itcompany.routes.status

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.itcompany.exeption.UrlException
import ru.itcompany.routes.status.dto.CreateStatusDto
import ru.itcompany.routes.status.dto.UpdateStatusDto
import ru.itcompany.routes.status.mapper.StatusMapper
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.routes.user.mapper.UserMapper
import ru.itcompany.service.status.StatusService
import ru.itcompany.service.user.UserService

fun Route.statusController() {
    val service: StatusService by inject()
    val mapper: StatusMapper by inject()
    val objectMapper: ObjectMapper by inject()
    authenticate("auth-jwt") {
        route("/statuses")
        {
            get()
            {
                service.getAll().let {
                    call.respond(objectMapper.writeValueAsString(it))
                }
            }
            get("/{offset}/{limit}")
            {
                val offset = call.parameters["offset"]?.toInt() ?: throw UrlException("Offset is not correct")
                val limit = call.parameters["limit"]?.toInt() ?: throw UrlException("Limit is not correct")
                service.getFromTo(offset, limit).let {
                    call.respond(objectMapper.writeValueAsString(it))
                }
            }
            post("/create")
            {

                service.create(
                    mapper.toCreateStatusArgument(call.receive<CreateStatusDto>())
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
                        mapper.toUpdateStatusArgument(call.receive<UpdateStatusDto>())
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