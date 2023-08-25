package ru.itcompany.configurations

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.itcompany.routes.appeal.appealController
import ru.itcompany.routes.authorization.authorizationController
import ru.itcompany.routes.message.messageController
import ru.itcompany.routes.status.statusController
import ru.itcompany.routes.user.userController

fun Application.configureRouting()
{
    routing {
        authorizationController()
        userController()
        statusController()
        appealController()
        messageController()
    }
}
