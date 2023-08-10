package ru.itcompany.config

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.itcompany.db.DatabaseFactory
import ru.itcompany.repository.user.UserRepository
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.service.authenticate.AuthenticateService
import ru.itcompany.service.authenticate.AuthenticateServiceImpl

val koinModules = module {
    single { DatabaseFactory(get()) }
    factory { JwtManager(get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factoryOf(::AuthenticateServiceImpl)
}