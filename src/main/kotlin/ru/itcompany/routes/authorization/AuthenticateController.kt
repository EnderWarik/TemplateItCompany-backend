package ru.itcompany.routes.authorization

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.itcompany.config.ServiceFactory
import ru.itcompany.routes.authorization.dto.AuthenticateUserDto
import ru.itcompany.routes.authorization.dto.RegisterUserDto
import ru.itcompany.routes.authorization.mappers.AuthenticateMapper


fun Route.authorizationController() {
    val service = ServiceFactory.getAuthenticateService()

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
