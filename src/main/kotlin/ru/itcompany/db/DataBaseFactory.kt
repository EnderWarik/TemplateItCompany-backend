package ru.itcompany.db

import com.zaxxer.hikari.*
import org.flywaydb.core.Flyway
import org.ktorm.database.Database

import ru.itcompany.config.ConfigHandler
import java.io.*

object DatabaseFactory{
    private lateinit var database:Database
    private lateinit var hikariDataSource:HikariDataSource
    fun init() {
        val password = ConfigHandler.getString("storage.password")
        val user = ConfigHandler.getString("storage.user")
        val jdbcURL = " jdbc:postgresql://"+ ConfigHandler.getString("storage.host") +
                ":" +
                ConfigHandler.getString("storage.port") +
                "/IT_company"
                (ConfigHandler.getStringOrNull("storage.dbFilePath")?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
        hikariDataSource = createHikariDataSource(
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
        val flyway = Flyway.configure().dataSource(hikariDataSource).load()
        flyway.migrate()
    }

    fun getDataBase():Database {
       return database
    }
}