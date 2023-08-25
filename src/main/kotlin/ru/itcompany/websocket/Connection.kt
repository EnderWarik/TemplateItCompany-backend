package ru.itcompany.websocket

import io.ktor.websocket.*

class Connection(val session: DefaultWebSocketSession, val userName: String)