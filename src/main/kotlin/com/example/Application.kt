package com.example

import com.example.database.DatabaseSingleton
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseSingleton.init()
    install(FreeMarker)
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
