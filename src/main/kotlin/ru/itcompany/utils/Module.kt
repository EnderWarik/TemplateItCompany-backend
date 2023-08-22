package ru.itcompany.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.ktorm.jackson.KtormModule

import ru.itcompany.repository.user.UserRepository
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.routes.user.mapper.UserMapper
import ru.itcompany.service.authenticate.AuthenticateService
import ru.itcompany.service.authenticate.AuthenticateServiceImpl
import ru.itcompany.service.user.UserService
import ru.itcompany.service.user.UserServiceImpl

val koinModules = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<UserService> { UserServiceImpl(get()) }
    factoryOf(::AuthenticateServiceImpl){ bind<AuthenticateService>() }
    factory{ObjectMapper().registerModule(KtormModule()) }
    factory{UserMapper() }
}