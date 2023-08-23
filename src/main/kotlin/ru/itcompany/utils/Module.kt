package ru.itcompany.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.ktorm.jackson.KtormModule
import ru.itcompany.repository.appeal.AppealRepository
import ru.itcompany.repository.appeal.AppealRepositoryImpl
import ru.itcompany.repository.message.MessageRepository
import ru.itcompany.repository.message.MessageRepositoryImpl
import ru.itcompany.repository.status.StatusRepository
import ru.itcompany.repository.status.StatusRepositoryImpl

import ru.itcompany.repository.user.UserRepository
import ru.itcompany.repository.user.UserRepositoryImpl
import ru.itcompany.routes.appeal.mapper.AppealMapper
import ru.itcompany.routes.message.mapper.MessageMapper
import ru.itcompany.routes.status.mapper.StatusMapper
import ru.itcompany.routes.user.mapper.UserMapper
import ru.itcompany.service.appeal.AppealService
import ru.itcompany.service.appeal.AppealServiceImpl
import ru.itcompany.service.authenticate.AuthenticateService
import ru.itcompany.service.authenticate.AuthenticateServiceImpl
import ru.itcompany.service.message.MessageService
import ru.itcompany.service.message.MessageServiceImpl
import ru.itcompany.service.status.StatusService
import ru.itcompany.service.status.StatusServiceImpl
import ru.itcompany.service.user.UserService
import ru.itcompany.service.user.UserServiceImpl
import java.text.SimpleDateFormat

val koinModules = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<UserService> { UserServiceImpl(get()) }
    factoryOf(::AuthenticateServiceImpl){ bind<AuthenticateService>() }
    factory<ObjectMapper>{
        ObjectMapper()
        .registerModule(KtormModule())
        .registerModule(JavaTimeModule())
        .setDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"))
    }
    factory{UserMapper() }
    factory{StatusMapper() }
    factory<StatusRepository> { StatusRepositoryImpl(get()) }
    factory<StatusService> { StatusServiceImpl(get()) }

    factory{AppealMapper() }
    factory<AppealRepository> { AppealRepositoryImpl(get()) }
    factoryOf(::AppealServiceImpl){ bind<AppealService>() }

    factory{MessageMapper() }
    factory<MessageRepository> { MessageRepositoryImpl(get()) }
    factoryOf(::MessageServiceImpl){ bind<MessageService>() }

}