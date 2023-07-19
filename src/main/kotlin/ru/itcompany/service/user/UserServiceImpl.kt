package ru.itcompany.service.user

import ru.itcompany.repository.user.UserRepository


class UserServiceImpl(private val repository: UserRepository) : UserService {

}