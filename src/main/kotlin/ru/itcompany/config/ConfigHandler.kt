package ru.itcompany.config

import io.ktor.server.application.*
import io.ktor.server.config.*

class ConfigHandler {

    companion object {
        fun getPort(config: ApplicationConfig) : Int?
        {
            return try {
                config.propertyOrNull("ktor.deployment.port")?.getString()?.toInt()
            }
            catch (e:RuntimeException)
            {
                throw RuntimeException("Неверно указан порт")
            }

        }
        fun getHost(config: ApplicationConfig) : String?
        {
            return try {
                config.propertyOrNull("ktor.deployment.host")?.getString()
            }
            catch (e:RuntimeException)
            {
                throw RuntimeException("Неверно указан порт")
            }

        }
    }


}