package ru.itcompany.service.user

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.mindrot.jbcrypt.BCrypt
import ru.itcompany.config.ConfigHandler
import ru.itcompany.repository.UserRepository
import ru.itcompany.service.user.argument.LoginUserArgument
import java.util.*


class UserService {
    companion object
    {
        val repository: UserRepository = UserRepository()
    }
    fun loginUser(loginUserArgument :LoginUserArgument ) : String?
    {
        return if(!isUserHaveAccess(loginUserArgument.email,loginUserArgument.password))
             null
        else
            JWT.create()
            .withAudience(ConfigHandler.getString("jwt.audience"))
            .withIssuer(ConfigHandler.getString("jwt.issuer"))
            .withClaim("email", loginUserArgument.email)
            .withExpiresAt(Date(System.currentTimeMillis() + ConfigHandler.getInt("jwt.lifeTimeMS")))
            .sign(Algorithm.HMAC256(ConfigHandler.getString("jwt.secret")))
//var password =  BCrypt.hashpw(user.password, BCrypt.gensalt())
    }
    private fun isUserHaveAccess(email:String,password:String):Boolean
    {
        return repository.isExist(email,password)
    }
}