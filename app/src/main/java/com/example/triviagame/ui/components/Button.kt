package com.example.triviagame.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A generic reusable button with arguments for button text and click listener.
 */
@Composable
fun AppDefaultButton(
    onButtonClicked: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onButtonClicked,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(buttonText)
    }
}