package ru.itcompany.configurations

import io.ktor.server.application.*
import io.ktor.server.websocket.*
import ru.itcompany.websocket.webSockets
import java.time.Duration

fun Application.configureSockets()
{
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    this.webSockets()
}
