package ru.itcompany.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import ru.itcompany.routes.authorizationController

fun Application.configureRouting() {
    routing {
        authorizationController()
    }
}
