package ru.itcompany.service.user.argument

import ru.itcompany.model.enum.UserRoleEnum

class UpdateUserArgument private constructor(
    val email: String,
    val password: String,
    val role: UserRoleEnum,
    val firstName: String,
    val lastName: String,
    val thirdName: String?,
    val address: String?,
    val phoneNumber: String?,
    val inn: String?,
    val organizationName: String?
)
{

    data class Builder(
        var email: String = "",
        var password: String = "",
        var role: UserRoleEnum = UserRoleEnum.Individual,
        var firstName: String = "",
        var lastName: String = "",
        var thirdName: String? = null,
        var address: String? = null,
        var phoneNumber: String? = null,
        var inn: String? = null,
        var organizationName: String? = null
    )
    {

        fun email(email: String) = apply { this.email = email }
        fun password(password: String) = apply { this.password = password }
        fun role(role: UserRoleEnum) = apply { this.role = role }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun thirdName(thirdName: String?) = apply { this.thirdName = thirdName }
        fun address(address: String?) = apply { this.address = address }
        fun phoneNumber(phoneNumber: String?) = apply { this.phoneNumber = phoneNumber }
        fun inn(inn: String?) = apply { this.inn = inn }
        fun organizationName(organizationName: String?) = apply { this.organizationName = organizationName }
        fun build() = UpdateUserArgument(
            email,
            password,
            role,
            firstName,
            lastName,
            thirdName,
            address,
            phoneNumber,
            inn,
            organizationName
        )
    }


}