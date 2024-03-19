package com.example.plugins

import com.example.database.dao
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val title: String,
    val price: Int,
)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(mapOf("movies" to listOf(Movie("Kingsman 1", 12500), Movie("Bladshot", 58000))))
        }

        get("/games") {
            call.respond(mapOf("games" to dao.allGames()))
        }

        // Static plugin. Try to access `/static/index.html`
//        static("/static") {
//            resources("static")
//        }
    }
}
