package ru.itcompany.db

import com.zaxxer.hikari.*
import org.flywaydb.core.Flyway
import org.koin.core.component.KoinComponent
import org.ktorm.database.Database

import ru.itcompany.config.ConfigHandler

import java.io.*

class DatabaseFactory(private val config: ConfigHandler) {
    private var database:Database
    private var hikariDataSource:HikariDataSource
    init {
        val password = config.getString("ktor.storage.password")
        val user = config.getString("ktor.storage.user")
        val host = config.getString("ktor.storage.host")
        val port =   config.getString("ktor.storage.port")
        val dbFilePath =config.getStringOrNull("ktor.storage.dbFilePath")
        val driver  =config.getString("ktor.storage.driver")
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
        database =  Database.connect(hikariDataSource)
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

    fun migrate() {
        val flyway = Flyway.configure().dataSource(hikariDataSource).load()
        flyway.migrate()
    }

    fun getDataBase():Database {
       return database
    }
}