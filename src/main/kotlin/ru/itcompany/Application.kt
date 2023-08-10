package ru.itcompany


import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin
import ru.itcompany.config.ConfigHandler

import ru.itcompany.config.koinModules
import ru.itcompany.configurations.*
import ru.itcompany.db.DatabaseFactory

fun main(args: Array<String>): Unit
{

    EngineMain.main(args)
}

fun Application.module() {
    val configModule = module{
        single { ConfigHandler(environment.config) }
    }
    koin { modules(configModule,koinModules)}
    val database:DatabaseFactory by inject()
    database.migrate()

    val config:ConfigHandler by inject()
    embeddedServer(
        CIO,
        port = config.getIntOrNull("ktor.deployment.port") ?: 8080,
        host = config.getStringOrNull("ktor.deployment.host") ?: "0.0.0.0",
        module =
        {
            configureSecurity()
            configureRouting()
            configureStatusPages()
            configureMonitoring()
            configureSerialization()
            configureSockets()

        }
    ).start(wait = true)


}


