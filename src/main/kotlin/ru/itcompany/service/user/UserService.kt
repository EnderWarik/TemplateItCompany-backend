package ru.itcompany.service.user

import ru.itcompany.repository.UserRepository


class UserService {
    companion object
    {
        val repository: UserRepository = UserRepository()
    }

}