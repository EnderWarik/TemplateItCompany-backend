package ru.itcompany.routes.authorization.mapper

import ru.itcompany.routes.authorization.dto.AuthenticateUserDto
import ru.itcompany.routes.authorization.dto.RegisterUserDto
import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.service.user.argument.RegisterUserArgument

class AuthenticateMapper
{
    fun toAuthenticateArgument(dto: AuthenticateUserDto): AuthenticateArgument
    {
        return AuthenticateArgument.Builder()
            .email(dto.email)
            .password(dto.password)
            .build()
    }

    fun toRegisterArgument(dto: RegisterUserDto): RegisterUserArgument
    {
        return RegisterUserArgument.Builder()
            .email(dto.email)
            .password(dto.password)
            .role(dto.role)
            .firstName(dto.firstName)
            .lastName(dto.lastName)
            .thirdName(dto.thirdName)
            .address(dto.address)
            .phoneNumber(dto.phoneNumber)
            .inn(dto.inn)
            .organizationName(dto.organizationName)
            .build()
    }
}