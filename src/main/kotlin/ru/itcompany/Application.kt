package ru.itcompany


import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.flywaydb.core.Flyway
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin
import org.ktorm.database.Database
import ru.itcompany.config.ConfigHandler
import ru.itcompany.config.JwtManager

import ru.itcompany.config.koinModules
import ru.itcompany.configurations.*

import ru.itcompany.db.DatabaseHikariDataSource

fun main(args: Array<String>): Unit
{

    EngineMain.main(args)
}

fun Application.module() {
    val dataSource = DatabaseHikariDataSource(environment.config).get()
    val database: Database = Database.connect(dataSource)
    val flyway = Flyway.configure().dataSource(dataSource).load()
    flyway.migrate()

    val databaseModule = module{
        single { database }
        factory { JwtManager(environment.config) }
    }
    koin { modules(databaseModule,koinModules)}
    embeddedServer(
        CIO,
        port = environment.config.propertyOrNull("ktor.deployment.port")?.getString()?.toInt() ?: 8080,
        host = environment.config.propertyOrNull("ktor.deployment.host")?.getString() ?: "0.0.0.0",
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


