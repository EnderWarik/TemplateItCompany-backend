package ru.itcompany.configurations

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import ru.itcompany.routes.Authorization.authorizationController

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        authorizationController(environment?.config)
    }
}
