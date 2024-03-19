package com.example.database

import com.example.models.GameModel

interface Dao {
    suspend fun allGames(): List<GameModel>
    suspend fun game(id: Int): GameModel?
    suspend fun addNewGame(title: String, body: String): GameModel?
    suspend fun editGame(id: Int, title: String, body: String): Boolean
    suspend fun deleteGame(id: Int): Boolean
}