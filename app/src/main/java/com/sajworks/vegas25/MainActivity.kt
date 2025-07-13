package com.sajworks.vegas25

import android.hardware.Sensor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sajworks.vegas25.ui.theme.SensorDataScreen
import com.sajworks.vegas25.ui.theme.Vegas25Theme
import com.sajworks.vegas25.view_models.SensorViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vegas25Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun MainContent(innerPadding: PaddingValues = PaddingValues(20.dp)) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        val viewModel: SensorViewModel = viewModel()
        val uiState by viewModel.sensorData.collectAsState()

        SensorDataScreen(viewModel = viewModel, sensorData = uiState)
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Vegas25Theme {
        MainContent()
    }
}