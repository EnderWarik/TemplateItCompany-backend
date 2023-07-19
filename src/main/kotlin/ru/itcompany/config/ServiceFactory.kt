package ru.itcompany.config

import ru.itcompany.db.DatabaseFactory
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.service.authenticate.AuthenticateService
import ru.itcompany.service.authenticate.AuthenticateServiceImpl
import ru.itcompany.service.user.UserService
import ru.itcompany.service.user.UserServiceImpl

object ServiceFactory {
    private val database = DatabaseFactory.getDataBase()
    private val userRepository: UserRepository = UserRepositoryImpl(database)
    private val userService: UserService = UserServiceImpl(userRepository)
    private val authenticateService: AuthenticateService = AuthenticateServiceImpl(userRepository)
    fun getAuthenticateService() : AuthenticateService
    {
        return authenticateService
    }
}