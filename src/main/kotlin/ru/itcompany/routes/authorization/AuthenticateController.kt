package ru.itcompany.routes.authorization

import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.itcompany.routes.authorization.dto.AuthenticateUserDto
import ru.itcompany.routes.authorization.dto.RegisterUserDto
import ru.itcompany.routes.authorization.mappers.AuthenticateMapper
import ru.itcompany.service.authenticate.AuthenticateService


fun Route.authorizationController() {
    val service = AuthenticateService()

    route("/auth")
    {

        post("/login") {
            service.authenticate(
                AuthenticateMapper.toAuthenticateArgument(
                    call.receive<AuthenticateUserDto>()
                )
            ).let {
                call.respond(hashMapOf("token" to it))
            }


        }
        post("/register")
        {

            service.register(
                AuthenticateMapper.toRegisterArgument(
                    call.receive<RegisterUserDto>()
                )
            ).let {
                call.respond(hashMapOf("token" to it))
            }
        }
    }
}
