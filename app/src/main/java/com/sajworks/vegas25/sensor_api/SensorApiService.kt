package com.sajworks.vegas25.sensor_api

import com.sajworks.vegas25.data_models.SensorData
import retrofit2.http.GET
import retrofit2.http.POST

interface SensorApiService {

    // Endpoint to fetch data
    @GET("api/data")
    suspend fun getSensorData(): SensorData

    // Endpoint to send data
    @POST("api/play")
    suspend fun play()
}