package com.example.triviagame.ui.scoreboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triviagame.R
import com.example.triviagame.TriviaGameTopAppBar
import com.example.triviagame.ui.home.HomeBody
import com.example.triviagame.ui.navigation.NavigationDestination

/**
 * Home Screen Navigate Destination
 */
object ScoreboardDestination : NavigationDestination {
    override val route = "scoreboard"
    override val titleRes = R.string.scoreboard_destination
}


@ExperimentalMaterial3Api
@Composable
fun ScoreboardScreen(
    navigateToChooseTopic: () -> Unit,
    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TriviaGameTopAppBar(
                title = stringResource(
                    id = R.string.scoreboard_destination
                ),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        ScoreboardBody(
            navigateToChooseTopic = navigateToChooseTopic,
            navigateToScoreResults = navigateToScoreResults,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        )
    }
}


/**
 * Home body content
 */
@Composable
fun ScoreboardBody(
    navigateToChooseTopic: () -> Unit,
    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
        ,
    ) {
        Text(
            text = stringResource(id = R.string.txt_scoreboard_results),
            modifier = Modifier.padding(16.dp)
        )

        // the score list
        ScoreList()


    }
}

/**
 * A list of scores, a list that has the following: score, date and time, and topic
 */
@Composable
fun ScoreList(
    modifier: Modifier = Modifier
) {
    ScoreListHeader()
    LazyColumn(
        modifier = modifier
        // add header
    ) {
                items(10) {
            ScoreListItem()
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
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
            )

    ) {
        Text(
            text = "Score",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = "Date",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = "Topic",
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
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f)
        )
    ) {
        Text(
            text = "10",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = "10/10/2021",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        Text(
            text = "General Knowledge",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
    }
}

/**
 * Preview the HomeScreen
 */
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ScoreboardScreen(
        navigateToChooseTopic = {},
        navigateToScoreResults = {},
    )
}

/**
 * Preview for the score list
 */
@Preview(showBackground = true)
@Composable
fun ScoreListPreview() {
    ScoreList()
}

/**
 * Preview for the score list item
 */
@Preview(showBackground = true)
@Composable
fun ScoreListItemPreview() {
    ScoreListItem()
}

/**
 * Preview for the score list header
 */
@Preview(showBackground = true)
@Composable
fun ScoreListHeaderPreview() {
    ScoreListHeader()
}