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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triviagame.R
import com.example.triviagame.TriviaGameTopAppBar
import com.example.triviagame.ui.components.AppDefaultButton
import com.example.triviagame.ui.navigation.NavigationDestination


/**
 * Choose Topic Screen Navigate Destination
 */
object GameTurnDestination : NavigationDestination {
    override val route = "game_turn"
    override val titleRes = R.string.game_turn_destination
}


/**
 * Choose Topic Screen
 */
@ExperimentalMaterial3Api
@Composable
fun GameTurnScreen(
//    navigateToChooseTopic: () -> Unit,
//    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TriviaGameTopAppBar(
                title = stringResource(
                    id = GameTurnDestination.titleRes
                ),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        GameTurnBody(
//            navigateToChooseTopic = navigateToChooseTopic,
//            navigateToScoreResults = navigateToScoreResults,
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
//    navigateToChooseTopic: () -> Unit,
//    navigateToScoreResults: () -> Unit,
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
            Text(text = "${stringResource(id = R.string.txt_score)}: 1000")

            // display the current turn
            Text(text = "${stringResource(id = R.string.txt_turn)}: 1")
        }

        // display the question
        Text(
            text = "What is the capital of France?",
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
                AppDefaultButton(buttonText = "Paris", onButtonClicked = {})
                // second button
                AppDefaultButton(buttonText = "London", onButtonClicked = {})
                // third button
                AppDefaultButton(buttonText = "Berlin", onButtonClicked = {})
                // fourth button
                AppDefaultButton(buttonText = "Madrid", onButtonClicked = {})
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