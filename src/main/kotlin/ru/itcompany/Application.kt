package ru.itcompany

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.itcompany.config.ConfigHandler
import ru.itcompany.configurations.*
import ru.itcompany.db.DatabaseFactory

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    ConfigHandler.init(environment.config)
    DatabaseFactory.init()
    DatabaseFactory.migrate()
    embeddedServer(
        CIO,
        port = ConfigHandler.getIntOrNull("ktor.deployment.port") ?: 8080 ,
        host =  ConfigHandler.getStringOrNull("ktor.deployment.host") ?: "0.0.0.0",
        module =
        {
            configureSecurity()
            configureRouting()
            configureMonitoring()
            configureSerialization()
            configureSockets()

        }
    ).start(wait = true)


}


