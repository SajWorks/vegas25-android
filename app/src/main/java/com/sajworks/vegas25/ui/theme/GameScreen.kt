package com.sajworks.vegas25.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sajworks.vegas25.data_models.GameStateData
import com.sajworks.vegas25.view_models.GameViewModel
import com.sajworks.vegas25.data_models.GameStateData.*
import com.sajworks.vegas25.data_models.Guess
import com.sajworks.vegas25.data_models.GuessResponse
import com.sajworks.vegas25.view_models.GameViewModel.*



@Composable
fun GameScreen(gameViewModel: GameViewModel, gameStateData: GameStateData?) {

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Mastermind Game",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Player 2 Wins!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow).forEach { color ->
                CircleView(color = color)
            }
        }

        Text(
            "Correct Pattern",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Make a guess",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow).forEach { color ->
                CircleView(color = color)
            }
        }

        Button(
            onClick = {
                // Submit a guess
            },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Enter")
        }

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            "Guess History",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            gameStateData?.guesses?.forEach { guess ->
                ViewGuess(guess = guess, gameViewModel = gameViewModel)
            }
        }
    }
}

fun mapResponseToColors(response: GuessResponse): List<Color> {
    val responseColors = mutableListOf<Color>()

    repeat(response.black) { responseColors.add(Color.Black) }
    repeat(response.white) { responseColors.add(Color.White) }

    while (responseColors.size < 4) {
        responseColors.add(Color.Transparent)
    }

    return responseColors
}
/// A row representing a single guess with 4 colored circles and 4 response circles
@Composable
fun ViewGuess(guess: Guess, gameViewModel: GameViewModel) {
    val colors = gameViewModel.mapGuessToColors(guess)
    val responseColors = mapResponseToColors(guess.response)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        colors.take(4).forEach { color ->
            CircleView(color = color)
        }
        Response(colors = responseColors)
    }
}

/// A view for one single color of a guess
@Composable
fun CircleView(color: Color) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape) // Clips the Box to a circle shape
            .background(color) // Sets the background color of the circle
    )
}

/// A view for the 2x2 grid of circles for the response to a guess
@Composable
fun Response(colors: List<Color>) {
    Box(
        modifier = Modifier.size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                SmallResponseCircle(color = colors.getOrNull(0))
                SmallResponseCircle(color = colors.getOrNull(1))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SmallResponseCircle(color = colors.getOrNull(2))
                SmallResponseCircle(color = colors.getOrNull(3))
            }
        }
    }
}

/// A view for one single color of a guess response
@Composable
fun SmallResponseCircle(color: Color?) {
    val fillColor = color ?: Color.Transparent
    Box(
        modifier = Modifier
            .size(23.dp)
            .border(BorderStroke(1.dp, Color.Gray), CircleShape)
            .clip(CircleShape)
            .background(fillColor)
    )
}

@Preview(showBackground = true)
@Composable
fun GuessPreview() {
    val guess = Guess(
        guess = "RED,GREEN,BLUE,YELLOW",
        response = GuessResponse(black = 2, white = 1)
    )
}
