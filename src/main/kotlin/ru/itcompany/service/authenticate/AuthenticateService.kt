package ru.itcompany.service.authenticate

import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.config.JwtManager
import ru.itcompany.exeption.AuthenticationException
import ru.itcompany.exeption.UserAlreadyExistException
import ru.itcompany.models.User
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.service.user.argument.RegisterUserArgument

interface AuthenticateService {

    fun authenticate(argument : AuthenticateArgument) : String

    fun register(argument: RegisterUserArgument) : String


}