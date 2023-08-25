package ru.itcompany.routes.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.json.JsonMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.itcompany.configurations.configureSerialization
import ru.itcompany.exeption.AuthenticationException
import ru.itcompany.exeption.UrlException
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.routes.user.mapper.UserMapper
import ru.itcompany.service.user.UserService
import ru.itcompany.utils.JwtManager


fun Route.userController() {
    val service: UserService by inject()
    val mapper: UserMapper by inject()
    val objectMapper: ObjectMapper by inject()
    val jwtManager: JwtManager by inject()
    authenticate("auth-jwt") {
        route("/users")
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
                    mapper.toCreateUserArgument(call.receive<CreateUserDto>())
                ).let {
                    call.respond(objectMapper.writeValueAsString(it))
                }

            }
            put("/update/{id}")
            {
                val id = call.parameters["id"]?.toLong()
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") ?: throw AuthenticationException("Error authorization header")
                val email = jwtManager.getEmailFromToken(token) ?: throw AuthenticationException("Error authorization header")
                if(id != null)
                {
                    service.update(
                        id,
                        mapper.toUpdateUserArgument(call.receive<UpdateUserDto>()),
                        email
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
            get("/{offset}/{limit}")
            {
                val offset = call.parameters["offset"]?.toInt() ?: throw UrlException("Offset is not correct")
                val limit = call.parameters["limit"]?.toInt() ?: throw UrlException("Limit is not correct")
                service.getFromTo(offset, limit)
            }
        }
    }

}
