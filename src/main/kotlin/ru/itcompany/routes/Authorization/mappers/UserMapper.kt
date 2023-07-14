package ru.itcompany.routes.Authorization.mappers

import org.ktorm.dsl.Query
import org.ktorm.dsl.forEach
import ru.itcompany.models.User
import ru.itcompany.routes.Authorization.dto.LoginUserDto
import ru.itcompany.service.user.argument.LoginUserArgument

object UserMapper {
    fun toLoginArgument(dto: LoginUserDto) : LoginUserArgument
    {
       return LoginUserArgument.Builder()
           .email(dto.email)
           .password(dto.password)
           .build()
    }
}