package ru.itcompany.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.authorizationController() {

    post("/login") {
        call.respondText("Hello World!")
    }

}
