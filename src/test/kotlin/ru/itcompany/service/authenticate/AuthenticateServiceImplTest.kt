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
import ru.itcompany.service.user.argument.RegisterUserArgument
import ru.itcompany.utils.JwtManager
import java.sql.Timestamp

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

        val findUser = User {
            email = "test@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
        }

        every { jwtManager.create(argument.email, findUser.role) } returns "some token ${argument.email}"
        val token = jwtManager.create(argument.email, findUser.role)


        every { repository.findByEmail(argument.email) } returns findUser
        every { repository.create(any()) } returns findUser

        val captor = slot<String>()

        val result = service.authenticate(argument)
        coVerify { repository.findByEmail(capture(captor)) }

        Assertions.assertEquals(captor.captured, argument.email)
        Assertions.assertEquals(token, result)
        Assertions.assertEquals(argument.email, captor.captured)
    }

    @Test
    fun register()
    {
        val argument: RegisterUserArgument = RegisterUserArgument.Builder()
            .email("root@mail.ru")
            .password("root")
            .role(UserRoleEnum.Admin)
            .firstName("Alex")
            .lastName("Alex lastname")
            .thirdName("Alex thirdName")
            .address("Alex address")
            .phoneNumber("123")
            .inn("123")
            .organizationName("org name")
            .build()

        every { jwtManager.create(argument.email, argument.role) } returns "some token ${argument.email}"
        val token = jwtManager.create(argument.email, argument.role)

        val findUser = User {
            id = 1
            email = argument.email
            password = BCrypt.hashpw(argument.password, BCrypt.gensalt())
            role = argument.role
            firstName = argument.firstName
            lastName = argument.lastName
            thirdName = argument.thirdName
            address = argument.address
            phoneNumber = argument.phoneNumber
            inn = argument.inn
            organizationName = argument.organizationName
            isDeleted = false
            dateCreate = Timestamp(System.currentTimeMillis())
        }
        every { repository.findByEmail(argument.email) } returns null
        every { repository.create(any()) } returns findUser

        val captor = slot<String>()

        val result = service.register(argument)

        coVerify { repository.findByEmail(capture(captor)) }

        Assertions.assertEquals(token, result)
        Assertions.assertEquals(argument.email, captor.captured)
    }
}