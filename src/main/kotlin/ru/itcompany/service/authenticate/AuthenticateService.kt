package ru.itcompany.service.authenticate

import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.service.user.argument.RegisterUserArgument

interface AuthenticateService {

    fun authenticate(argument : AuthenticateArgument) : String

    fun register(argument: RegisterUserArgument) : String


}