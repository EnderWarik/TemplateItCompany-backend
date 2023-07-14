package ru.itcompany.configurations

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.itcompany.config.ConfigHandler
import ru.itcompany.config.JwtConfig
import ru.itcompany.config.JwtConfig.jwtRealm

fun Application.configureSecurity() {

    // Please read the jwt property from the config file if you are using EngineMain

    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(JwtConfig.verifierToken())
            validate { credential -> JwtConfig.validateToken(credential) }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
            }

        }
    }
}


