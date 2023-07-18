package ru.itcompany.db

import com.zaxxer.hikari.*
import org.flywaydb.core.Flyway
import org.ktorm.database.Database

import ru.itcompany.config.ConfigHandler
import ru.itcompany.models.users
import java.io.*

object DatabaseFactory{
    private lateinit var database:Database
    private lateinit var hikariDataSource:HikariDataSource
    fun init() {
        val password = ConfigHandler.getString("ktor.storage.password")
        val user = ConfigHandler.getString("ktor.storage.user")
        val host = ConfigHandler.getString("ktor.storage.host")
        val port =   ConfigHandler.getString("ktor.storage.port")
        val dbFilePath =ConfigHandler.getStringOrNull("ktor.storage.dbFilePath")
        val driver  =ConfigHandler.getString("ktor.storage.driver")
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