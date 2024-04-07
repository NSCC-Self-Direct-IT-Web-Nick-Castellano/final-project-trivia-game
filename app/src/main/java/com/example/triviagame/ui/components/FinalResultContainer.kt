package com.example.triviagame.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triviagame.R

@Composable
fun FinalResultContainer(
    modifier: Modifier = Modifier
) {
    // the column wrapper of the final result screen
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // score results
        Text(
            text = "${stringResource(id = R.string.txt_score)}: 10000",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        // date and time
        Text(
            text = "${stringResource(id = R.string.txt_date)}: 2021-10-10 10:10:10",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        // game topic
        Text(
            text = "${stringResource(id = R.string.txt_topic)}: General Knowledge",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/**
 * Preview Final Result Container
 */
@Composable
@Preview(showBackground = true)
fun PreviewFinalResultContainer() {
    FinalResultContainer()
}