package com.sajworks.vegas25.sensor_api

import com.sajworks.vegas25.data_models.GameStateData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApiService {
    @GET("api/game_state")
    suspend fun getGameStateData(): GameStateData

    @POST("api/guess")
    suspend fun sendGuess(@Query("guess") guess: String)
}