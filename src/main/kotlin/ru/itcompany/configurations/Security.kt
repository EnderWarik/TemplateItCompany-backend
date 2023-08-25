package ru.itcompany.configurations

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject
import ru.itcompany.utils.JwtManager


fun Application.configureSecurity()
{
    val jwtManager: JwtManager by inject()
    authentication {
        jwt("auth-jwt") {
            realm = jwtManager.jwtRealm
            verifier(jwtManager.verifierToken())
            validate { credential -> jwtManager.validateToken(credential) }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
            }


        }
        jwt("only-Admin") {
            realm = jwtManager.jwtRealm
            verifier(jwtManager.verifierToken())
            validate { credential -> jwtManager.validateAdminToken(credential) }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
            }


        }
    }
}


