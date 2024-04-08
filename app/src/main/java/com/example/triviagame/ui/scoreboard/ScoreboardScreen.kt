package com.example.triviagame.ui.scoreboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.triviagame.R
import com.example.triviagame.TriviaGameTopAppBar
import com.example.triviagame.data.model.Score
import com.example.triviagame.ui.AppViewModelProvider
import com.example.triviagame.ui.components.ScoreList
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
    onNavigateBack: () -> Unit = {},
    viewModel: ScoreboardViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scoreboardUiState = viewModel.scoreboardUiState.collectAsState()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TriviaGameTopAppBar(
                title = stringResource(
                    id = ScoreboardDestination.titleRes
                ),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onNavigateBack
            )
        },
    ) { innerPadding ->
        ScoreboardBody(
            scores = scoreboardUiState.value.scoreboardList,
            getTriviaTopicName = {
                viewModel.getTriviaTopicName(it)
                viewModel.triviaTopicName
            },
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
    scores: List<Score> = listOf(),
    getTriviaTopicName: (Long) -> String,
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
        ScoreList(
            scores = scores,
            getTriviaTopicName = getTriviaTopicName,
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
    ScoreboardScreen()
}
