package com.example.triviagame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triviagame.R
import com.example.triviagame.data.model.Score


/**
 * A list of scores, a list that has the following: score, date and time, and topic
 */
@Composable
fun ScoreList(
    scores: List<Score> = listOf(),
    getTriviaTopicName: (Long) -> String,
    modifier: Modifier = Modifier
) {
    ScoreListHeader()

    LazyColumn(
        modifier = modifier
        // add header
    ) {
        items(scores) {
            ScoreListItem(
                score = it,
                getTriviaTopicName = getTriviaTopicName
            )
        }
    }
}

/**
 * The header row for the score list
 */
@Composable
fun ScoreListHeader(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)
            )
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
            )

    ) {
        Text(
            text = stringResource(id = R.string.txt_score),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = stringResource(id = R.string.txt_num_questions_answered),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = stringResource(id = R.string.txt_date),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = stringResource(id = R.string.txt_topic),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
    }
}


/**
 * Score list item
 */
@Composable
fun ScoreListItem(
    score: Score,
    getTriviaTopicName: (Long) -> String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                // only on bottom
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.75f)
            )
            .background(
                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f)
            )
    ) {
        Text(
            text = score.finalScore.toString(),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = score.questionsAnswered.toString(),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = score.date,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = getTriviaTopicName(score.triviaTopicId),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
    }
}


/**
 * Preview for the score list
 */
@Preview(showBackground = true)
@Composable
fun ScoreListPreview() {
    ScoreList(
        scores = listOf(
            Score(
                id = 1,
                userName = "John Doe",
                finalScore = 10,
                questionsAnswered = 10,
                triviaTopicId = 1,
                date = "10/10/2021"
            )
        ),
        getTriviaTopicName = { "Trivia Topic" }
    )
}

/**
 * Preview for the score list item
 */
@Preview(showBackground = true)
@Composable
fun ScoreListItemPreview() {
    ScoreListItem(
        score = Score(
            id = 1,
            userName = "John Doe",
            finalScore = 10,
            questionsAnswered = 10,
            triviaTopicId = 1,
            date = "10/10/2021"
        ),
        getTriviaTopicName = { "Trivia Topic" }
    )
}

/**
 * Preview for the score list header
 */
@Preview(showBackground = true)
@Composable
fun ScoreListHeaderPreview() {
    ScoreListHeader()
}