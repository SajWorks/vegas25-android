package com.sajworks.vegas25.sensor_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SensorApiClient {
    private const val BASE_URL = "https://amazing-crane-ghastly.ngrok-free.app/"

    val api: SensorApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SensorApiService::class.java)
    }
}