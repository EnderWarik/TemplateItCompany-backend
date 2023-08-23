package ru.itcompany.configurations

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import ru.itcompany.exeption.*
import ru.itcompany.exeption.user.UserAlreadyExistException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(status = HttpStatusCode.Unauthorized, message = cause.message ?: "Authentication failed!")
        }
        exception<NotFoundEntityException> { call, cause ->
            call.respond(status = HttpStatusCode.NotFound, message = cause.message ?: "Entity not found")
        }
        exception<DataBaseException> { call, cause ->
            call.respond(status = HttpStatusCode.InternalServerError, message = cause.message ?: "Problem of interaction with the database")
        }
        exception<UserAlreadyExistException> { call, cause ->
            call.respond(status = HttpStatusCode.Conflict, message = cause.message ?: "User already exist")
        }
    }
}