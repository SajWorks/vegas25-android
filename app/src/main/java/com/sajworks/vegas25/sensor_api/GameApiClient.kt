package com.sajworks.vegas25.sensor_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GameApiClient {
    private const val BASE_URL = "https://amazing-crane-ghastly.ngrok-free.app/"
    val api: GameApiService by lazy {
        Retrofit.Builder()
            .baseUrl(GameApiClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GameApiService::class.java)
    }
}

