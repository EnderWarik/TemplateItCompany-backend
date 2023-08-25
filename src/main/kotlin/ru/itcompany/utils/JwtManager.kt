package ru.itcompany.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import java.util.*

class JwtManager(config: ApplicationConfig)
{
    private val jwtAudience = config.property("ktor.security.jwt.audience").getString()
    private val jwtDomain = config.property("ktor.security.jwt.issuer").getString()
    val jwtRealm = config.property("ktor.security.jwt.realm").getString()
    private val jwtSecret = config.property("ktor.security.jwt.secret").getString()
    private val jwtIssuer = config.property("ktor.security.jwt.issuer").getString()
    private val lifeRime = config.property("ktor.security.jwt.lifeTimeMS").getString().toInt()
    fun verifierToken(): JWTVerifier
    {
        return JWT
            .require(Algorithm.HMAC256(jwtSecret))
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .build()
    }

    fun validateToken(credential: JWTCredential): JWTPrincipal?
    {
        return if (credential.payload.getClaim("email").asString() != ""
            && credential.payload.audience.contains(jwtAudience)
            && credential.payload.issuer.contains(jwtDomain)
        )
        {
            JWTPrincipal(credential.payload)
        } else
        {
            null
        }

    }

    fun validateAdminToken(credential: JWTCredential): JWTPrincipal?
    {
        return if (credential.payload.getClaim("email").asString() != "" &&
            credential.payload.audience.contains(jwtAudience) &&
            credential.payload.issuer.contains(jwtDomain) &&
            credential.payload.getClaim("role").asString() == "Admin"
        )
        {
            JWTPrincipal(credential.payload)
        } else
        {
            null
        }

    }

    fun create(email: String): String
    {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + lifeRime))
            .sign(Algorithm.HMAC256(jwtSecret))
    }

    fun getEmailFromToken(token: String): String?
    {
        val verifier = verifierToken()
        val jwt = try
        {
            verifier.verify(token)
        } catch (e: JWTVerificationException)
        {
            return null
        }
        return jwt.getClaim("email").asString()
    }
}