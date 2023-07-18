package ru.itcompany.routes.authorization.mappers

import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.routes.authorization.dto.AuthenticateUserDto
import ru.itcompany.routes.authorization.dto.RegisterUserDto
import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.service.user.argument.RegisterUserArgument

object AuthenticateMapper {
    fun toAuthenticateArgument(dto: AuthenticateUserDto) : AuthenticateArgument
    {
       return AuthenticateArgument.Builder()
           .email(dto.email)
           .password(dto.password)
           .build()
    }
    fun toRegisterArgument(dto: RegisterUserDto) : RegisterUserArgument
    {
        return RegisterUserArgument.Builder()
            .email(dto.email)
            .password(BCrypt.hashpw(dto.password, BCrypt.gensalt()))
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