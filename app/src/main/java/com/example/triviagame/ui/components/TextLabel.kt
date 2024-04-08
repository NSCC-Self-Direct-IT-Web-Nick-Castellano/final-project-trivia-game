package com.example.triviagame.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * A generic text label component.
 */
@Composable
fun AppDefaultTextLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
    )
}

/**
 * The text container that you lost the game.
 */
@Composable
fun GameOverTextLabel(
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp, 24.dp)
    ) {
        Text(
            text = "You lost the game!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
    }

}