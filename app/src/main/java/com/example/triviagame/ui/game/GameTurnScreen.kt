package com.example.triviagame.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.triviagame.data.model.Question
import com.example.triviagame.ui.AppViewModelProvider
import com.example.triviagame.ui.components.AppDefaultButton
import com.example.triviagame.ui.navigation.NavigationDestination


/**
 * Choose Topic Screen Navigate Destination
 */
object GameTurnDestination : NavigationDestination {
    override val route = "game_turn"
    override val titleRes = R.string.game_turn_destination
    const val topicIdArg : Long = 1
    val routeWithArgs = "$route/{${topicIdArg}}"
}


/**
 * Choose Topic Screen
 */
@ExperimentalMaterial3Api
@Composable
fun GameTurnScreen(
    triviaTopicId: Long = 1,
    viewModel: GameTurnViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNavigateBack:  () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val gameTurnUiState = viewModel.gameTurnUiState.collectAsState()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TriviaGameTopAppBar(
                title = stringResource(
                    id = GameTurnDestination.titleRes
                ),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onNavigateBack
            )
        },
    ) { innerPadding ->
        GameTurnBody(
            score = gameTurnUiState.value.score,
            turn = gameTurnUiState.value.turnNumber,
            loseGame = gameTurnUiState.value.lose,
            question = gameTurnUiState.value.question,
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
fun GameTurnBody(
    score: Int = 1000,
    turn: Int = 1,
    loseGame: Boolean = false,
    question: Question,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // display the current score and the turn in a row
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // display the current score
            Text(text = "${stringResource(id = R.string.txt_score)}: ${score}")

            // display the current turn
            Text(text = "${stringResource(id = R.string.txt_turn)}: ${turn}")
        }

        // display the question
        Text(
            text = question.question,
//            text = "What is the capital of France?",
            modifier = Modifier.padding(24.dp)
        )

        // display a grid of 4x1 buttons with the answer options, these have to be clickable
        // and have a change in color when clicked
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)

        ) {

                // first button
                AppDefaultButton(buttonText = question.choiceA, onButtonClicked = {})
                // second button
                AppDefaultButton(buttonText = question.choiceB, onButtonClicked = {})
                // third button
                AppDefaultButton(buttonText = question.choiceC, onButtonClicked = {})
                // fourth button
                AppDefaultButton(buttonText = question.choiceD, onButtonClicked = {})
        }
    }
}


/**
 * Preview the HomeScreen
 */
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ChooseTopicScreenPreview() {
    GameTurnScreen(
//        navigateToChooseTopic = {},
//        navigateToScoreResults = {},
    )
}