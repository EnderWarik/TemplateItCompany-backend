package ru.itcompany.configurations

import io.ktor.server.websocket.*
import java.time.Duration
import io.ktor.server.application.*
import ru.itcompany.websocket.webSockets

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    this.webSockets()
}
