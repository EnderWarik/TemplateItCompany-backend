package ru.itcompany.service.user.argument

class UserAccessArgument private constructor(
    var email: String,
    var password: String
) {

    data class Builder(
        var email: String = "",
        var password: String = ""
    ) {
        fun email(email: String)= apply { this.email = email }
        fun password(password: String) = apply { this.password = password }
        fun build() = UserAccessArgument(email, password)
    }

}