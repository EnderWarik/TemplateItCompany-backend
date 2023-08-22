package ru.itcompany.routes.user.mapper

import ru.itcompany.model.enum.UserRoleEnum
import ru.itcompany.routes.user.dto.CreateUserDto
import ru.itcompany.routes.user.dto.UpdateUserDto
import ru.itcompany.service.user.argument.CreateUserArgument
import ru.itcompany.service.user.argument.UpdateUserArgument

class UserMapper {

    fun toUpdateUserArgument(dto: UpdateUserDto): UpdateUserArgument
    {
        return UpdateUserArgument.Builder()
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
    fun toCreateUserArgument(dto: CreateUserDto): CreateUserArgument
    {
        return CreateUserArgument.Builder()
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
