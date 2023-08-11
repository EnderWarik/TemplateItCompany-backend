package ru.itcompany.db

import com.zaxxer.hikari.*
import io.ktor.server.config.*
import org.flywaydb.core.Flyway

import ru.itcompany.config.ConfigHandler

import java.io.*

class DatabaseHikariDataSource(var config: ApplicationConfig) {
    private var hikariDataSource:HikariDataSource
    init {
        val password = config.property("ktor.storage.password").getString()
        val user = config.property("ktor.storage.user").getString()
        val host = config.property("ktor.storage.host").getString()
        val port =   config.property("ktor.storage.port").getString()
        val dbFilePath = config.propertyOrNull("ktor.storage.dbFilePath")?.getString()
        val driver  = config.property("ktor.storage.driver").getString()
        val jdbcURL = " jdbc:postgresql://" +
                host +
                ":" +
                port +
                "/IT_company" +
                (dbFilePath?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
        hikariDataSource = createHikariDataSource(
        url = jdbcURL,
        dbPassword = password,
        dbUser = user,
        driver = driver
        )

    }

    private fun createHikariDataSource(
        url: String,
        dbPassword: String,
        dbUser: String,
        driver: String
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        password = dbPassword
        username = dbUser
        maximumPoolSize = 3
        isAutoCommit = true
        transactionIsolation = "TRANSACTION_READ_COMMITTED"
        schema = "public"
        validate()
    })

    fun get(): HikariDataSource
    {
        return hikariDataSource
    }


}