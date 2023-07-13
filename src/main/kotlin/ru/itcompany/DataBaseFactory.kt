package ru.itcompany

import com.zaxxer.hikari.*
import io.ktor.server.config.*
import kotlinx.coroutines.*
import org.flywaydb.core.Flyway
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select

import ru.itcompany.config.ConfigHandler
import ru.itcompany.models.Users
import java.io.*

object DatabaseFactory{
    private lateinit var database:Database
    private lateinit var hikariDataSource:HikariDataSource
    fun init(config: ApplicationConfig) {
        val password = config.property("storage.password").getString()
        val user = config.property("storage.user").getString()
        val jdbcURL = " jdbc:postgresql://"+ config.property("storage.host").getString() +
                ":" +
                config.property("storage.port").getString() +
                "/IT_company"
                (config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
        hikariDataSource=createHikariDataSource(
        url = jdbcURL,
        dbPassword = password,
        dbUser = user
        )
        database = Database.connect(hikariDataSource)



    }

    private fun createHikariDataSource(
        url: String,
        dbPassword: String,
        dbUser: String
    ) = HikariDataSource(HikariConfig().apply {

        jdbcUrl = url
        password = dbPassword
        username = dbUser
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        schema = "public"
        validate()
    })

    fun migrate() {
        val flyway = Flyway.configure().cleanDisabled(false).dataSource(hikariDataSource).load()
        flyway.migrate()
    }

    fun getDataBase():Database {
       return database
    }
}