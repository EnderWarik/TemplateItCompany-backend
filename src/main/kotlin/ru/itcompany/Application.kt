package ru.itcompany

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.itcompany.config.ConfigHandler
import ru.itcompany.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)
    embeddedServer(
        CIO,
        port = ConfigHandler.getPort(environment.config) ?: 8080 ,
        host =  ConfigHandler.getHost(environment.config) ?: "0.0.0.0",
        module =
        {
            configureRouting()
            configureMonitoring()
            configureSerialization()
            configureSockets()
            configureSecurity()
        }
    ).start(wait = true)
}


