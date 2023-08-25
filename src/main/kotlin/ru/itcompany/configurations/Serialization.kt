package ru.itcompany.configurations

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.ktorm.jackson.KtormModule
import java.text.SimpleDateFormat

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
        jackson {
            setSerializationInclusion(JsonInclude.Include.ALWAYS)
            registerModule(KtormModule())
            registerModule(JavaTimeModule())
            dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        }
    }
}
