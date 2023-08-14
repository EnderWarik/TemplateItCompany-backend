package ru.itcompany.service.authenticate

import io.mockk.*
import junit.framework.TestCase
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.ktorm.database.Database
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.config.JwtManager
import ru.itcompany.models.User
import ru.itcompany.models.enum.UserRoleEnum
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.service.authenticate.argument.AuthenticateArgument

class AuthenticateServiceImplTest {

    val repository = mockk<UserRepositoryImpl>(relaxed = true)
    val jwtManager = mockk<JwtManager>()
    val service = AuthenticateServiceImpl(repository,jwtManager )


    @Test
    fun authenticate() {

        val argument : AuthenticateArgument = AuthenticateArgument.Builder()
            .email("test@mail.ru")
            .password("test")
            .build()

        every { jwtManager.create(argument.email) } returns "some token ${argument.email}"
        val token = jwtManager.create(argument.email)

        val findUser = User{
            email = "test@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
        }
        every { repository.findByEmail(argument.email) } returns findUser
        every { repository.create(any()) } returns Unit

        val captor = slot<String>()

        val result = service.authenticate(argument)

        coVerify { repository.findByEmail(capture(captor))}

        Assertions.assertEquals(token, result)
        Assertions.assertEquals(argument.email, captor.captured)
    }

    @Test
    fun register() {
    }
}