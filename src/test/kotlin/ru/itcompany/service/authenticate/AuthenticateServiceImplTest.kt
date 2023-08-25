package ru.itcompany.service.authenticate

import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.model.User
import ru.itcompany.model.enum.UserRoleEnum
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.utils.JwtManager

class AuthenticateServiceImplTest
{

    val repository = mockk<UserRepositoryImpl>(relaxed = true)
    val jwtManager = mockk<JwtManager>()
    val service = AuthenticateServiceImpl(repository, jwtManager)


    @Test
    fun authenticate()
    {

        val argument: AuthenticateArgument = AuthenticateArgument.Builder()
            .email("test@mail.ru")
            .password("test")
            .build()

        every { jwtManager.create(argument.email) } returns "some token ${argument.email}"
        val token = jwtManager.create(argument.email)

        val findUser = User {
            email = "test@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
        }
        every { repository.findByEmail(argument.email) } returns findUser
        every { repository.create(any()) } returns findUser

        val captor = slot<String>()

        val result = service.authenticate(argument)

        coVerify { repository.findByEmail(capture(captor)) }

        Assertions.assertEquals(token, result)
        Assertions.assertEquals(argument.email, captor.captured)
    }

    @Test
    fun register()
    {
    }
}