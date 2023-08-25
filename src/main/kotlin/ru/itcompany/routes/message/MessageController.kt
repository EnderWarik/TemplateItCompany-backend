package ru.itcompany.routes.message

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.itcompany.exeption.UrlException
import ru.itcompany.routes.message.dto.CreateMessageDto
import ru.itcompany.routes.message.dto.UpdateMessageDto
import ru.itcompany.routes.message.mapper.MessageMapper
import ru.itcompany.service.message.MessageService

fun Route.messageController()
{
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
                    mapper.toCreateMessageArgument(call.receive<CreateMessageDto>())
                ).let {
                    call.respond(objectMapper.writeValueAsString(it))
                }

            }
            put("/update/{id}")
            {
                val id = call.parameters["id"]?.toLong()
                if (id != null)
                {
                    service.update(
                        id,
                        mapper.toUpdateMessageArgument(call.receive<UpdateMessageDto>())
                    ).let {
                        call.respond(objectMapper.writeValueAsString(it))
                    }
                } else
                {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            delete("/delete/{id}")
            {
                val id = call.parameters["id"]?.toLong()
                if (id != null)
                {
                    service.delete(id)
                    call.respond(HttpStatusCode.OK)
                } else
                {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}