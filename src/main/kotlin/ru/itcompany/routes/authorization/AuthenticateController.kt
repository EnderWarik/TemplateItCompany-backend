package ru.itcompany.routes.authorization

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.itcompany.routes.authorization.dto.AuthenticateUserDto

import ru.itcompany.routes.authorization.dto.RegisterUserDto
import ru.itcompany.routes.authorization.mapper.AuthenticateMapper
import ru.itcompany.service.authenticate.AuthenticateService


fun Route.authorizationController()
{
    val service: AuthenticateService by inject()
    val mapper = AuthenticateMapper()
    route("/auth")
    {
        post("/login") {
            service.authenticate(
                mapper.toAuthenticateArgument(
                    call.receive<AuthenticateUserDto>()
                )
            ).let {
                call.respond(hashMapOf("token" to it))
            }


        }
        post("/register")
        {

            service.register(
                mapper.toRegisterArgument(
                    call.receive<RegisterUserDto>()
                )
            ).let {
                call.respond(hashMapOf("token" to it))
            }
        }
    }
}
