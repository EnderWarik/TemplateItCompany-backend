package ru.itcompany.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*
import java.util.*

object JwtManager {
    private val jwtAudience = ConfigHandler.getString("ktor.security.jwt.audience")
    private val jwtDomain = ConfigHandler.getString("ktor.security.jwt.issuer")
    val jwtRealm = ConfigHandler.getString("ktor.security.jwt.realm")
    private val jwtSecret = ConfigHandler.getString("ktor.security.jwt.secret")
    private val jwtIssuer = ConfigHandler.getString("ktor.security.jwt.issuer")
    private val lifeRime = ConfigHandler.getInt("ktor.security.jwt.lifeTimeMS")
    fun verifierToken():JWTVerifier
    {
        return JWT
            .require(Algorithm.HMAC256(jwtSecret))
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .build()
    }
    fun validateToken(credential:JWTCredential):JWTPrincipal?
    {
      return  if (credential.payload.getClaim("email").asString() != ""
            && credential.payload.audience.contains(jwtAudience)
            && credential.payload.issuer.contains(jwtDomain)) {
            JWTPrincipal(credential.payload)
        } else {
            null
        }

    }
    fun create(email:String): String
    {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + lifeRime))
            .sign(Algorithm.HMAC256(jwtSecret))
    }

}