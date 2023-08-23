package ru.itcompany.websocket

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import org.koin.ktor.ext.inject
import ru.itcompany.exeption.user.UserAlreadyExistException
import ru.itcompany.model.Appeal
import ru.itcompany.model.User
import ru.itcompany.service.appeal.AppealService
import ru.itcompany.service.message.MessageService
import ru.itcompany.service.message.argument.CreateMessageArgument
import ru.itcompany.service.user.UserService
import ru.itcompany.utils.JwtManager
import java.util.*
import kotlin.collections.LinkedHashSet

 fun Application.webSockets()
{

    val connections = Collections.synchronizedMap<Long, MutableList<Connection>>(LinkedHashMap())
    routing {

        val messageService: MessageService by inject()
        val userService: UserService by inject()
        val appealService: AppealService by inject()
        val jwtManager: JwtManager by inject()

        suspend fun handleCommand(connectionsForAppeal: MutableList<Connection>,connection: Connection, userName: String, command: String) {
            when (command) {
                "leave" -> {
                    connectionsForAppeal -= connection
                }
                else -> {
                    connectionsForAppeal.find { it.userName == userName }?.session?.send("Unknown command: $command")
                }
            }
        }
        fun createMessage(appealId: Long, ownerId: Long, content: String)
        {
            messageService.create(
                CreateMessageArgument.Builder()
                    .appealId(appealId)
                    .ownerId(ownerId)
                    .content(content)
                    .build()
            )
        }

        webSocket("/chat/{appealId}") {
            val appealId = call.parameters["appealId"]?.toLong() ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.NORMAL,
                    "Invalid chat ID"
                )
            )
            val appeal: Appeal = appealService.findById(appealId) ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.INTERNAL_ERROR,
                    "Appeal is not exist"
                )
            )
            val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                ?: return@webSocket close(CloseReason(CloseReason.Codes.NORMAL, "Invalid token"))
            val email = jwtManager.getEmailFromToken(token) ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.NORMAL,
                    "Invalid email in token"
                )
            )
            val user = userService.findByEmail(email) ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.NORMAL,
                    "Invalid user"
                )
            )

            if ((appeal.userCreator.id != user.id) and (appeal.userEmployee?.id != user.id)) {
                return@webSocket close(CloseReason(CloseReason.Codes.NORMAL, "Not a member of this chat"))
            }
            val userName = "${user.firstName} + ${user.lastName}"
            val connection = Connection(this, userName)
            if (!connections.containsKey(appealId)) {
                connections[appealId] = mutableListOf()
            }
            val connectionsForAppeal = connections[appealId]!!
            connectionsForAppeal += connection
            try {
                send("You are connected to the chat '${appeal.title}'!")
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val receivedText = frame.readText()
                        if (receivedText.startsWith("/")) {
                            handleCommand(connectionsForAppeal, connection, userName, receivedText.substring(1))
                        } else {
                            val textWithUsername = "[${userName}]: $receivedText"
                            createMessage(appealId, user.id, receivedText)
                            connectionsForAppeal.forEach {
                                it.session.send(textWithUsername)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                connectionsForAppeal -= connection
                if (connectionsForAppeal.isEmpty()) {
                    connections.remove(appealId)
                }
            }
        }

    }



}


