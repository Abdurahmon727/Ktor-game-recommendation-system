package com.example

import com.example.database.DatabaseSingleton
import com.example.plugins.configureHTTP
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.cors.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseSingleton.init()
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
    install(FreeMarker)
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting()

}
