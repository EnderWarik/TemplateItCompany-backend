package ru.itcompany


import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.koin.dsl.module
import org.koin.ktor.plugin.koin
import org.ktorm.database.Database
import ru.itcompany.configurations.*
import ru.itcompany.db.DatabaseHikariDataSource
import ru.itcompany.utils.JwtManager
import ru.itcompany.utils.koinModules


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module()
{
    val dataSource = DatabaseHikariDataSource(environment.config).get()
    val database: Database = Database.connect(dataSource)
    val needMigrate = environment.config.property("ktor.flyway.migrate").getString()
    val needClean = environment.config.property("ktor.flyway.clean").getString()

    if (needClean == "True" || needClean == "true")
    {
        val flyway = Flyway.configure().cleanDisabled(false).dataSource(dataSource).load()
        flyway.clean()
    }
    if (needMigrate == "True" || needMigrate == "true")
    {
        val flyway = Flyway.configure().dataSource(dataSource).load()
        flyway.migrate()
    }


    val applicationModule = module {
        single { database }
        factory { JwtManager(environment.config) }
    }
    koin { modules(applicationModule, koinModules) }

    configureSerialization()
    configureSecurity()
    configureRouting()
    configureStatusPages()
    configureMonitoring()
    configureSockets()


}


