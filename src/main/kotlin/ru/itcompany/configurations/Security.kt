package ru.itcompany.configurations

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.itcompany.config.JwtManager
import ru.itcompany.config.JwtManager.jwtRealm

fun Application.configureSecurity() {

    // Please read the jwt property from the config file if you are using EngineMain

    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(JwtManager.verifierToken())
            validate { credential -> JwtManager.validateToken(credential) }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
            }


        }
    }
}


