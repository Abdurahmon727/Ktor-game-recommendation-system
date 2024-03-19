package com.example.database

import com.example.database.DatabaseSingleton.dbQuery
import com.example.models.GameModel
import com.example.models.GamesModel
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll


class DaoImpl : Dao {
    private fun resultRowToGameModel(row: ResultRow) = GameModel(
        id = row[GamesModel.id],
        title = row[GamesModel.title],
        body = row[GamesModel.body],
    )

    override suspend fun allGames(): List<GameModel> = dbQuery {
        GamesModel.selectAll().map(::resultRowToGameModel)
    }

    override suspend fun game(id: Int): GameModel? = dbQuery {
        GamesModel.select { GamesModel.id eq id }.map(::resultRowToGameModel).singleOrNull()
    }

    override suspend fun addNewGame(title: String, body: String): GameModel? = dbQuery {
        val insert = GamesModel.insert {
            it[GamesModel.title] = title
            it[GamesModel.body] = body
        }
        insert.resultedValues?.singleOrNull()?.let(::resultRowToGameModel)
    }

    override suspend fun editGame(id: Int, title: String, body: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGame(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}
val dao: Dao = DaoImpl().apply {
    runBlocking {
        if(allGames().isEmpty()) {
            addNewGame("The drive to develop!", "...it's what keeps me going.")
        }
    }
}