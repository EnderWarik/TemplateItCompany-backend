package ru.itcompany.controller.user


import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.controller.user.response.GetUserResponse
import ru.itcompany.model.enum.UserRoleEnum
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import kotlin.test.Test
import kotlin.test.assertEquals

class UserControllerTest
{

    val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ1c2VycyIsImlzcyI6Im1hZHByb2plY3QuZXciLCJlbWFpbCI6InJvb3QxMDFAcm9vdC5yb290Iiwicm9sZSI6IkFkbWluIiwiZXhwIjoxNjkzMDcwNjc5fQ.5LcnashaJFWgLYMEQGYHELS2tjtqrzASKHjiWSHE9bU"

    @Test
    fun createUserTest() = testApplication {
        environment {
            config = ApplicationConfig("applicationTest.conf")
        }
        val userDto = CreateUserDto(
            email = "rootTest@root.root",
            password = "root",
            role = UserRoleEnum.LegalEntity,
            firstName = "Alex",
            lastName = "Moskalev",
            thirdName = "Dmitrievich",
            address = "Tyrgeneva 46",
            phoneNumber = "89146775291",
            inn = "82821234",
            organizationName = "madProject.ew"
        )
        val requestBody = Json.encodeToString(userDto)

        val response = client.post("/users/create")
        {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(requestBody)
        }

        assertEquals(HttpStatusCode.OK, response.status)

        val responseBody = response.bodyAsText()
        val responseUser = Json.decodeFromString<GetUserResponse>(responseBody)

        assertEquals(true, BCrypt.checkpw("root", responseUser.password))

        val expectedResponseUser = GetUserResponse(
            id = 1,
            email = "rootTest@root.root",
            password = responseUser.password,
            role = UserRoleEnum.LegalEntity,
            firstName = "Alex",
            lastName = "Moskalev",
            thirdName = "Dmitrievich",
            address = "Tyrgeneva 46",
            phoneNumber = "89146775291",
            inn = "82821234",
            organizationName = "madProject.ew",
            isDeleted = false,
            dateCreate = responseUser.dateCreate
        )
        val expectedResponseBody = Json.encodeToString(expectedResponseUser)

        assertEquals(expectedResponseBody, responseBody)
    }

    @Test
    fun getAllUserTest() = testApplication {
        environment {
            config = ApplicationConfig("applicationTest.conf")
        }
        val userDto = CreateUserDto(
            email = "rootTest@root.root",
            password = "root",
            role = UserRoleEnum.LegalEntity,
            firstName = "Alex",
            lastName = "Moskalev",
            thirdName = "Dmitrievich",
            address = "Tyrgeneva 46",
            phoneNumber = "89146775291",
            inn = "82821234",
            organizationName = "madProject.ew"
        )
        val requestBody1 = Json.encodeToString(userDto)

        val response1 = client.post("/users/create")
        {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(requestBody1)
        }

        assertEquals(HttpStatusCode.OK, response1.status)

        val userDto2 = CreateUserDto(
            email = "rootTest2@root.root",
            password = "root",
            role = UserRoleEnum.LegalEntity,
            firstName = "Alex",
            lastName = "Moskalev",
            thirdName = "Dmitrievich",
            address = "Tyrgeneva 46",
            phoneNumber = "89146775291",
            inn = "82821234",
            organizationName = "madProject.ew"
        )
        val requestBody2 = Json.encodeToString(userDto2)

        val response2 = client.post("/users/create")
        {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(requestBody2)
        }

        assertEquals(HttpStatusCode.OK, response2.status)

        val response = client.get("/users")
        {
            method = HttpMethod.Get
            bearerAuth(token)
        }

        val responseBody = response.bodyAsText()
        val responseUsers = Json.decodeFromString<List<GetUserResponse>>(responseBody)

        assertEquals(true, BCrypt.checkpw("root", responseUsers[0].password))
        assertEquals(true, BCrypt.checkpw("root", responseUsers[1].password))

        val expectedResponseUsers = listOf<GetUserResponse>(
            GetUserResponse(
                id = 1,
                email = "rootTest@root.root",
                password = responseUsers[0].password,
                role = UserRoleEnum.LegalEntity,
                firstName = "Alex",
                lastName = "Moskalev",
                thirdName = "Dmitrievich",
                address = "Tyrgeneva 46",
                phoneNumber = "89146775291",
                inn = "82821234",
                organizationName = "madProject.ew",
                isDeleted = false,
                dateCreate = responseUsers[0].dateCreate
            ),
            GetUserResponse(
                id = 2,
                email = "rootTest2@root.root",
                password = responseUsers[1].password,
                role = UserRoleEnum.LegalEntity,
                firstName = "Alex",
                lastName = "Moskalev",
                thirdName = "Dmitrievich",
                address = "Tyrgeneva 46",
                phoneNumber = "89146775291",
                inn = "82821234",
                organizationName = "madProject.ew",
                isDeleted = false,
                dateCreate = responseUsers[1].dateCreate
            )
        )
        val expectedResponseBody = Json.encodeToString(expectedResponseUsers)

        assertEquals(expectedResponseBody, responseBody)
    }

    @Test
    fun updateUserTest() = testApplication {
        environment {
            config = ApplicationConfig("applicationTest.conf")
        }
        val userDto = CreateUserDto(
            email = "rootTest@root.root",
            password = "root",
            role = UserRoleEnum.Admin,
            firstName = "Alex",
            lastName = "Moskalev",
            thirdName = "Dmitrievich",
            address = "Tyrgeneva 46",
            phoneNumber = "89146775291",
            inn = "82821234",
            organizationName = "madProject.ew"
        )
        val requestCreateBody = Json.encodeToString(userDto)

        val responseCreateUser = client.post("/users/create")
        {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(requestCreateBody)
        }

        assertEquals(HttpStatusCode.OK, responseCreateUser.status)

        val userUpdateDto = UpdateUserDto(
            email = "rootTest1@root.root",
            password = "root1",
            role = UserRoleEnum.Admin,
            firstName = "Alex1",
            lastName = "Moskalev1",
            thirdName = "Dmitrievich1",
            address = "Tyrgeneva 461",
            phoneNumber = "891467752911",
            inn = "828212341",
            organizationName = "madProject.ew1"
        )
        val requestUpdateBody = Json.encodeToString(userUpdateDto)

        val responseUpdateUser = client.put("/users/update/1")
        {
            method = HttpMethod.Put
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(requestUpdateBody)
        }
        assertEquals(HttpStatusCode.OK, responseUpdateUser.status)

        val responseBody = responseUpdateUser.bodyAsText()
        val response = Json.decodeFromString<GetUserResponse>(responseBody)

        assertEquals(true, BCrypt.checkpw("root1", response.password))

        val expectedResponseUser = GetUserResponse(
            id = 1,
            email = "rootTest1@root.root",
            password = response.password,
            role = UserRoleEnum.Admin,
            firstName = "Alex1",
            lastName = "Moskalev1",
            thirdName = "Dmitrievich1",
            address = "Tyrgeneva 461",
            phoneNumber = "891467752911",
            inn = "828212341",
            organizationName = "madProject.ew1",
            isDeleted = false,
            dateCreate = response.dateCreate
        )
        val expectedResponseBody = Json.encodeToString(expectedResponseUser)

        assertEquals(expectedResponseBody, responseBody)
    }

    @Test
    fun deleteUserTest() = testApplication {
        environment {
            config = ApplicationConfig("applicationTest.conf")
        }
        val userCreateDto = CreateUserDto(
            email = "rootTest@root.root",
            password = "root",
            role = UserRoleEnum.LegalEntity,
            firstName = "Alex",
            lastName = "Moskalev",
            thirdName = "Dmitrievich",
            address = "Tyrgeneva 46",
            phoneNumber = "89146775291",
            inn = "82821234",
            organizationName = "madProject.ew"
        )
        val requestCreateBody = Json.encodeToString(userCreateDto)

        val responseCreateUser = client.post("/users/create")
        {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(requestCreateBody)
        }

        assertEquals(HttpStatusCode.OK, responseCreateUser.status)

        val response = client.delete("/users/delete/1")
        {
            method = HttpMethod.Delete
            bearerAuth(token)
        }

        assertEquals(HttpStatusCode.OK, response.status)

    }

}
