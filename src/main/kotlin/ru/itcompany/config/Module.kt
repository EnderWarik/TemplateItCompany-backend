package ru.itcompany.config

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

import ru.itcompany.repository.user.UserRepository
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.service.authenticate.AuthenticateServiceImpl

val koinModules = module {

    factory<UserRepository> { UserRepositoryImpl(get()) }
    factoryOf(::AuthenticateServiceImpl)
}