package ru.itcompany.routes.Authorization

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.config.ConfigHandler
import ru.itcompany.routes.Authorization.dto.LoginUserDto
import ru.itcompany.routes.Authorization.mappers.UserMapper
import ru.itcompany.service.user.UserService
import ru.itcompany.service.user.argument.LoginUserArgument
import java.util.*


fun Route.authorizationController(config: ApplicationConfig?) {
    val service: UserService = UserService()
    route("/users")
    {
        authenticate("auth-jwt") {
            post("/auth")
            {

            }

        }
        post("/login") {
            service.loginUser(
                UserMapper.toLoginArgument(
                    call.receive<LoginUserDto>()
                )
            ) ?.let {
                call.respond(hashMapOf("token" to it))
            } ?: call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
        }
    }
}
