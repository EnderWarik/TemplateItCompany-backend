package ru.itcompany.routes.user

import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.userController() {
    authenticate("auth-jwt") {
        post("/auth")
        {

        }
    }
}