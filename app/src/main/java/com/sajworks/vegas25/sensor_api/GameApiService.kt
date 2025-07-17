package com.sajworks.vegas25.sensor_api

import com.sajworks.vegas25.data_models.GameStateData
import retrofit2.http.GET

interface GameApiService {
    @GET("api/game_state")
    suspend fun getGameStateData(): GameStateData
}