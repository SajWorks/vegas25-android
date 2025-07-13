package com.sajworks.vegas25.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajworks.vegas25.data_models.SensorData
import com.sajworks.vegas25.sensor_api.SensorApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SensorViewModel : ViewModel() {
    private val _sensorData = MutableStateFlow<SensorData?>(null)
    val sensorData: StateFlow<SensorData?> = _sensorData

    private val api = SensorApiClient.api

    init {
        viewModelScope.launch {
            while (true) {
                try {
                    val data = api.getSensorData()
                    _sensorData.value = data
                } catch (e: Exception) {
                    // Log or handle error
                    println("Received exception = $e")
                }
                delay(3000)
            }
        }
    }

    fun play() {
        viewModelScope.launch {
            api.play()
        }
    }
}