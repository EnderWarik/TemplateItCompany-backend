//package ru.itcompany.repository.user
//
//import com.zaxxer.hikari.HikariConfig
//import com.zaxxer.hikari.HikariDataSource
//import io.mockk.every
//import io.mockk.mockk
//import org.flywaydb.core.Flyway
//import org.junit.Before
//import org.junit.jupiter.api.Test
//
//import org.junit.jupiter.api.Assertions.*
//import org.ktorm.database.Database
//import org.ktorm.entity.EntitySequence
//import org.ktorm.entity.sequenceOf
//import org.mindrot.jbcrypt.BCrypt
//import ru.itcompany.db.safeTransaction
//import ru.itcompany.model.User
//import ru.itcompany.model.dao.UserDao
//import ru.itcompany.model.dao.users
//import ru.itcompany.model.enum.UserRoleEnum
//class DatabaseFactoryForUnitTest
//{
//
//    lateinit var source: HikariDataSource
//
//     fun connect(): Database
//     {
//         source = createHikariDataSource()
//        return Database.connect(source)
//
//    }
//
//    private fun createHikariDataSource() = HikariDataSource(HikariConfig().apply {
//        driverClassName = "org.h2.Driver"
//        jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
//        maximumPoolSize = 3
//        isAutoCommit = true
//        validate()
//    })
//
//}
//class UserRepositoryImplTest
//{
//
//
//
//
//    @Test
//    fun getAll()
//    {
//        val database = DatabaseFactoryForUnitTest()
//        val repsository = UserRepositoryImpl(database.connect())
//        val flyway = Flyway.configure().dataSource(database.source).load()
//        flyway.migrate()
//        val usersExist:List<User> = listOf(
//            User {
//            email = "test@email.ru"
//            password = BCrypt.hashpw("test", BCrypt.gensalt())
//            role = UserRoleEnum.Individual
//            firstName = "FirstName"
//            lastName = "lastName"
//        },
//            User {
//            email = "test@email.ru"
//            password = BCrypt.hashpw("test", BCrypt.gensalt())
//            role = UserRoleEnum.Individual
//            firstName = "FirstName"
//            lastName = "lastName"
//            isDeleted = true
//        })
//        val usersExist2 = listOf<UserDao>()
//
//
//        val result = repsository.getAll()
//
//        val expected:List<User> = listOf(
//            User {
//                email = "test@email.ru"
//                password = BCrypt.hashpw("test", BCrypt.gensalt())
//                role = UserRoleEnum.Individual
//                firstName = "FirstName"
//                lastName = "lastName"
//            }
//            )
//
//        assertEquals(expected, result)
//    }
//
//    @Test
//    fun getAllBy()
//    {
//    }
//
//    @Test
//    fun findByEmail()
//    {
//    }
//
//    @Test
//    fun getFromTo()
//    {
//    }
//
//    @Test
//    fun totalRecords()
//    {
//    }
//
//    @Test
//    fun create()
//    {
//    }
//
//    @Test
//    fun getFirstBy()
//    {
//    }
//
//    @Test
//    fun getFirstOrNullBy()
//    {
//    }
//
//    @Test
//    fun update()
//    {
//    }
//
//    @Test
//    fun delete()
//    {
//    }
//}