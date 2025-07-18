package com.sajworks.vegas25.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajworks.vegas25.data_models.GameStateData
import com.sajworks.vegas25.data_models.Guess
import com.sajworks.vegas25.data_models.GuessResponse

import com.sajworks.vegas25.sensor_api.GameApiClient

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import com.sajworks.vegas25.ui.theme.Orange

class GameViewModel : ViewModel() {
    private val _gameStateData = MutableStateFlow<GameStateData?>(null)
    val gameStateData: StateFlow<GameStateData?> = _gameStateData
    private val _guessHistory = MutableStateFlow<List<Guess>>(emptyList())
    val guessHistory: StateFlow<List<Guess>> = _guessHistory

    private val api = GameApiClient.api
    fun mapGuessToColors(guess: Guess): List<Color> {
        // Split the guess string by one character and map to Color
        return mapStringToColors(guess.guess)

    }
    fun mapStringToColors(guessString: String): List<Color>{
        return guessString.chunked(1).map { colorName ->
            when (colorName.trim().lowercase()) {
                "r" -> Color.Red
                "g" -> Color.Green
                "b" -> Color.Blue
                "y" -> Color.Yellow
                "o" -> Color(0xFFFFA500) // Orange
                "p" -> Color(0xFF800080)  // Purple
                // Add more colors if needed
                else -> Color.Gray // fallback for unknown colors
            }
        }
    }
    fun mapColorsToGuess(colors: List<Color>): String {
        return colors.joinToString ("") { color ->
            when (color) {
                Color.Red -> "r"
                Color.Yellow -> "y"
                Color.Blue -> "b"
                Color(0xFF800080) -> "p"
                Color(0xFFFFA500) -> "o"
                Color.Green -> "g"
                else -> "r"
            }
        }// to call     val colorList = listOf(Color.Red, Color.Green, Color.Blue)
        //val guessString = mapColorsToGuess(colorList)
        //api.sendGuess(guessString)

    }

    init {
        viewModelScope.launch {
            while (true) {
                try {
                    val data = api.getGameStateData()
                    _gameStateData.value = data
                    println("${_gameStateData.value}")
                } catch (e: Exception) {
                    // Log or handle error
                    println("Received exception = $e")
                }
                delay(3000)
            }
        }
    }
    fun submitGuess(guess: String){
        viewModelScope.launch {
            api.sendGuess(guess)
        }
    }
}
