package com.sajworks.vegas25.data_models

data class GameStateData(
    val state: String,
    val winner: String?,
    val secret_pattern: String,
    val guesses: List<Guess>
)

data class Guess(
    val guess: String,
    val response: GuessResponse
)

data class GuessResponse(
    val black: Int,
    val white: Int
)
