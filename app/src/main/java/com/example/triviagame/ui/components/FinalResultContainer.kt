package com.example.triviagame.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triviagame.R


/**
 * Home body content
 */
@Composable
fun FinalResultButtonWrapper(
    score: Int = 0,
    date: String = "2021-10-10 10:10:10",
    topic: String = "General Knowledge",
    correctAnsers: Int = 0,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
    ) {
        Divider()

        Text(
            text = stringResource(id = R.string.txt_game_over),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )

        FinalResultContainer(
            score = score,
            date = date,
            topic = topic,
            correctAnsers = correctAnsers,
        )


        // a row with start over and go back to home screen button
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // start over button
            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(stringResource(id = R.string.btn_start_over))
            }
            // go back to home screen button
            Button(onClick = { /*TODO*/ }) {
                Text(stringResource(id = R.string.btn_back_to_home))
            }
        }
    }
}
@Composable
fun FinalResultContainer(
    score: Int = 0,
    date: String = "2021-10-10 10:10:10",
    topic: String = "General Knowledge",
    correctAnsers: Int = 0,
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
            text = "${stringResource(id = R.string.txt_score)}: $score",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        // the number of correct answers
        Text(
            text = "${stringResource(id = R.string.txt_correct_answers)}: $correctAnsers",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        // date and time
        Text(
            text = "${stringResource(id = R.string.txt_date)}: $date",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        // game topic
        Text(
            text = "${stringResource(id = R.string.txt_topic)}: $topic",
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

    FinalResultContainer(
            score = 1000,
        date = "2021-10-10 10:10:10",
        topic = "General Knowledge", )
}

/**
 * Preview Final Result Button Wrapper
 */
@Composable
@Preview(showBackground = true)
fun PreviewFinalResultButtonWrapper() {
    FinalResultButtonWrapper(
        score = 1000,
        date = "2021-10-10 10:10:10",
        topic = "General Knowledge",
    )
}