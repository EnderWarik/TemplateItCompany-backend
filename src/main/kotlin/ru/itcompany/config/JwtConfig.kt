package ru.itcompany.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*

object JwtConfig {
    private val jwtAudience = ConfigHandler.getString("jwt.audience")
    private val jwtDomain = ConfigHandler.getString("jwt.issuer")
    val jwtRealm = ConfigHandler.getString("jwt.realm")
    private val jwtSecret = ConfigHandler.getString("jwt.secret")
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

}