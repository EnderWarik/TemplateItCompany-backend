package ru.itcompany.config

import io.ktor.server.application.*
import io.ktor.server.config.*

object ConfigHandler {

    lateinit var  config:ApplicationConfig

    fun init(config:ApplicationConfig)
    {
       this.config = config
    }
    fun getInt(property:String) : Int
    {
        return try {
            config.property(property).getString().toInt()
        }
        catch (e:RuntimeException)
        {
            throw RuntimeException("Отсутствует или неверно указан $property")
        }

    }
    fun getString(property:String) : String
    {
        return try {
            config.property(property).getString()
        }
        catch (e:RuntimeException)
        {
            throw RuntimeException("Отсутствует или неверно указан $property")
        }

    }
    fun getIntOrNull(property:String) : Int?
    {
        return try {
            config.propertyOrNull(property)?.getString()?.toInt()
        }
        catch (e:RuntimeException)
        {
            throw RuntimeException("Неверно указан $property")
        }

    }
    fun getStringOrNull(property:String) : String?
    {
        return try {
            config.propertyOrNull(property)?.getString()
        }
        catch (e:RuntimeException)
        {
            throw RuntimeException("Неверно указан $property")
        }

    }



}