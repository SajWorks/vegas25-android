package com.sajworks.vegas25.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajworks.vegas25.data_models.SensorData
import com.sajworks.vegas25.view_models.SensorViewModel

@Composable
fun SensorDataScreen(viewModel: SensorViewModel, sensorData: SensorData?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SajWorks Vegas25 Project",
                style = TextStyle(
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Temperature: ${sensorData?.tempF ?: "--"} °F")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Temperature: ${sensorData?.tempC ?: "--"} °C")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Humidity: ${sensorData?.humidity ?: "--"} %")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.play() }) {
                Text("PLAY")
            }
        }
    }
}