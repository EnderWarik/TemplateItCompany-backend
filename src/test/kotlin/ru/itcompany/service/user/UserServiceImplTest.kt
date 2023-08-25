package ru.itcompany.service.user

import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.ktorm.expression.BinaryExpression
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.model.Meta
import ru.itcompany.model.User
import ru.itcompany.model.dao.UserDao
import ru.itcompany.model.enum.UserRoleEnum
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.service.PaginationResponse
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument

class UserServiceImplTest
{
    private val repository = mockk<UserRepositoryImpl>(relaxed = true)
    private val service = UserServiceImpl(repository)

    @Test
    fun getAll()
    {
        val usersExpected: List<User> = listOf(
            User {
                email = "test@email.ru"
                password = BCrypt.hashpw("test", BCrypt.gensalt())
                role = UserRoleEnum.Individual
                firstName = "FirstName"
                lastName = "lastName"
            }
        )
        every { repository.getAll() } returns usersExpected

        val result = service.getAll()

        assertEquals(usersExpected, result)
    }

    @Test
    fun findByEmail()
    {

        val user = User {
            email = "test@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
        }

        every { repository.findByEmail(any()) } returns user


        val captor = slot<String>()

        val result = service.findByEmail(user.email)

        coVerify { repository.findByEmail(capture(captor)) }
        assertEquals(captor.captured, user.email)

        assertEquals(user, result)
    }

    @Test
    fun getFromTo()
    {
        val usersExist: List<User> = listOf(
            User {
                email = "test@email.ru"
                password = BCrypt.hashpw("test", BCrypt.gensalt())
                role = UserRoleEnum.Individual
                firstName = "FirstName"
                lastName = "lastName"
            },
            User {
                email = "test1@email.ru"
                password = BCrypt.hashpw("test", BCrypt.gensalt())
                role = UserRoleEnum.Individual
                firstName = "FirstName"
                lastName = "lastName"
                isDeleted = true
            })
        val offset: Int = 0
        val limit: Int = 1

        every { repository.totalRecords() } returns 2
        every { repository.getFromTo(offset, limit) } returns usersExist

        val captorOffset = slot<Int>()
        val captorLimit = slot<Int>()

        val result = service.getFromTo(offset, limit)

        coVerify { repository.getFromTo(capture(captorOffset), capture(captorLimit)) }

        assertEquals(captorOffset.captured, offset)
        assertEquals(captorLimit.captured, limit)

        val expectedResponse = PaginationResponse(
            data = usersExist,
            meta = Meta(
                totalCounts = repository.totalRecords(),
                limit = limit,
                offset = offset
            )
        )
        assertEquals(expectedResponse, result)
    }

    @Test
    fun create()
    {
        val argument = CreateUserArgument.Builder()
            .email("test@email.ru")
            .password("test")
            .role(UserRoleEnum.Individual)
            .firstName("FirstName")
            .lastName("lastName")
            .build()

        val expectedUser = User {
            email = "test@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
            thirdName = null
            address = null
            phoneNumber = null
            inn = null
            organizationName = null
            isDeleted = false
        }
        every { repository.findByEmail(any()) } returns null
        every { repository.create(any()) } returns expectedUser
        val result = service.create(argument)

        val emailCaptor = slot<String>()
        val userCaptor = slot<User>()
        coVerify { repository.findByEmail(capture(emailCaptor)) }
        coVerify { repository.create(capture(userCaptor)) }

        if (BCrypt.checkpw(argument.password, userCaptor.captured.password))
        {
            expectedUser.password = userCaptor.captured.password
        }


        assertEquals(emailCaptor.captured, argument.email)
        assertEquals(userCaptor.captured, expectedUser)

        assertEquals(expectedUser.email, result.email)
    }

    @Test
    fun update()
    {

        val argument = UpdateUserArgument.Builder()
            .email("test@email.ru")
            .password("test")
            .role(UserRoleEnum.Individual)
            .firstName("FirstName")
            .lastName("lastName")
            .build()

        val userExist = User {
            id = 1
            email = "1@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
        }
        val userUpdaterEmail = "1@email.ru"

        val expectedUser = User {
            id = 1
            email = "test@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
            thirdName = null
            address = null
            phoneNumber = null
            inn = null
            organizationName = null
        }

        every { repository.getFirstBy(any()) } returns userExist
        every { repository.findByEmail(any()) } returns null
        every { repository.update(any()) } returns expectedUser

        val result = service.update(userExist.id, argument, userUpdaterEmail)

        val predicateCaptor = slot<(UserDao) -> BinaryExpression<Boolean>>()
        val emailCaptor = slot<String>()
        val updateCaptor = slot<User>()

        coVerify { repository.getFirstBy(capture(predicateCaptor)) }
        coVerify { repository.findByEmail(capture(emailCaptor)) }
        coVerify { repository.update(capture(updateCaptor)) }
        println(BCrypt.checkpw(argument.password, result.password))
        if (BCrypt.checkpw(argument.password, updateCaptor.captured.password))
        {

            expectedUser.password = updateCaptor.captured.password
        }
        assertEquals(emailCaptor.captured, argument.email)
        assertEquals(updateCaptor.captured, expectedUser)

        assertEquals(expectedUser.email, result.email)
    }

    @Test
    fun delete()
    {
        val argument = 1L

        val userExist = User {
            id = 1
            email = "1@email.ru"
            password = BCrypt.hashpw("test", BCrypt.gensalt())
            role = UserRoleEnum.Individual
            firstName = "FirstName"
            lastName = "lastName"
        }

        every { repository.getFirstBy(any()) } returns userExist
        every { repository.delete(any()) } returns Unit

        service.delete(argument)

        val captor = slot<User>()

        coVerify { repository.delete(capture(captor)) }

        assertEquals(captor.captured, userExist)
    }
}