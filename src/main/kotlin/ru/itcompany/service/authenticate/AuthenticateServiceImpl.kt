package ru.itcompany.service.authenticate

import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.utils.JwtManager
import ru.itcompany.exeption.AuthenticationException
import ru.itcompany.exeption.user.UserAlreadyExistException
import ru.itcompany.model.User
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.service.authenticate.argument.AuthenticateArgument
import ru.itcompany.service.user.argument.RegisterUserArgument

class AuthenticateServiceImpl(private val repository: UserRepository,private val jwtManager: JwtManager) : AuthenticateService {

    override fun authenticate(argument : AuthenticateArgument) : String
    {
        val user = repository.findByEmail(argument.email)
        if (user !== null && BCrypt.checkpw(argument.password,user.password))
        {
            return jwtManager.create(argument.email)
        }
        else throw AuthenticationException("Wrong email or password")
    }
    override fun register(argument: RegisterUserArgument) : String
    {
        repository.findByEmail(argument.email)?.let { throw UserAlreadyExistException("User already exist") }
        repository.create(User{
            email = argument.email
            password = BCrypt.hashpw(argument.password, BCrypt.gensalt())
            role= argument.role
            firstName= argument.firstName
            lastName= argument.lastName
            thirdName= argument.thirdName
            address= argument.address
            phoneNumber= argument.phoneNumber
            inn= argument.inn
            organizationName= argument.organizationName
            isDeleted = false
        })
        return jwtManager.create(argument.email)
    }

}