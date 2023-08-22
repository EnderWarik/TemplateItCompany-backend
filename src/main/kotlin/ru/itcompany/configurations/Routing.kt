package ru.itcompany.configurations

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import ru.itcompany.routes.authorization.authorizationController
import ru.itcompany.routes.status.statusController
import ru.itcompany.routes.user.userController

fun Application.configureRouting() {

    routing {
        authorizationController()
        userController()
        statusController()
    }
}
