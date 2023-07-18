package ru.itcompany.service.authenticate

import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.config.JwtManager
import ru.itcompany.exeption.AuthenticationException
import ru.itcompany.exeption.UserAlreadyExistException
import ru.itcompany.models.User
import ru.itcompany.repository.UserRepository
import ru.itcompany.service.user.UserService
import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.service.user.argument.RegisterUserArgument

class AuthenticateService {
    companion object
    {
        val repository: UserRepository = UserRepository()
    }
    fun authenticate(argument : AuthenticateArgument) : String
    {
        val user = repository.findByEmail(argument.email)
        if (user !== null && BCrypt.checkpw(argument.password,user.password))
        {
            return JwtManager.create(argument.email)
        }
        else throw AuthenticationException("Wrong email or password")
    }
    fun register(argument: RegisterUserArgument) : String
    {
        repository.findByEmail(argument.email)?.let { throw UserAlreadyExistException("User already exist") }
        UserService.repository.create(User{
            email = argument.email
            password = argument.password
            role= argument.role
            firstName= argument.firstName
            lastName= argument.lastName
            thirdName= argument.thirdName
            address= argument.address
            phoneNumber= argument.phoneNumber
            inn= argument.inn
            organizationName= argument.organizationName
        })
        return JwtManager.create(argument.email)
    }

}