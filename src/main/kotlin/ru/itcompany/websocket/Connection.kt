package ru.itcompany.websocket

import io.ktor.websocket.*
import ru.itcompany.model.User
import java.util.concurrent.atomic.AtomicInteger

class Connection(val session: DefaultWebSocketSession, userName: String) {

    val userName = userName
}