package ru.itcompany

import com.zaxxer.hikari.*
import io.ktor.server.config.*
import kotlinx.coroutines.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*
import ru.itcompany.config.ConfigHandler
import java.io.*

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val dataSource = config.property("storage.dataSource").getString()
        val password = config.property("storage.password").getString()
        val user = config.property("storage.user").getString()
        val jdbcURL = " jdbc:postgresql://"+ config.property("storage.host").getString() +
                ":" +
                config.property("storage.port").getString() +
                "/IT_company"
                (config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
       val database = createHikariDataSource(
        url = jdbcURL,
        dataSource = dataSource,
        dbPassword = password,
        dbUser = user
        )
       Database.connect(database)

        val flyway = Flyway.configure().dataSource(database).load()

        flyway.migrate()
    }

    private fun createHikariDataSource(
        url: String,
        dataSource: String,
        dbPassword: String,
        dbUser: String
    ) = HikariDataSource(HikariConfig().apply {
        dataSourceClassName = dataSource
        jdbcUrl = url
        password = dbPassword
        username = dbUser
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        schema = "public"
        validate()
    })
}