package ru.itcompany.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import java.io.File

class DatabaseHikariDataSource(config: ApplicationConfig)
{
    private var hikariDataSource: HikariDataSource

    init
    {
        val password = config.property("ktor.storage.password").getString()
        val user = config.property("ktor.storage.user").getString()
        val host = config.property("ktor.storage.host").getString()
        val port = config.property("ktor.storage.port").getString()
        val dbFilePath = config.propertyOrNull("ktor.storage.dbFilePath")?.getString()
        val tableName = config.propertyOrNull("ktor.storage.tableName")?.getString()
        val driver = config.property("ktor.storage.driver").getString()
        val jdbcURL = " jdbc:postgresql://" +
                host +
                ":" +
                port +
                "/" +
                tableName +
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