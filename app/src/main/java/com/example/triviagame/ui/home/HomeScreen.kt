package com.example.triviagame.ui.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.triviagame.R
import com.example.triviagame.TriviaGameTopAppBar
import com.example.triviagame.ui.components.AppDefaultButton
import com.example.triviagame.ui.components.AppDefaultTextLabel
import com.example.triviagame.ui.navigation.NavigationDestination

/**
 * Home Screen Navigate Destination
 */
object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_destination
}


@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
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
                    id = R.string.app_name
                ) + " - " + stringResource(
                    id = R.string.home_destination
                ),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        HomeBody(
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
fun HomeBody(
    navigateToChooseTopic: () -> Unit,
    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {

        // a welcome message
        AppDefaultTextLabel(text = stringResource(id = R.string.txt_welcome_screen))

        // a button to navigate to the Choose Topic screen
        AppDefaultButton(
            onButtonClicked = navigateToChooseTopic,
            buttonText = stringResource(id = R.string.btn_choose_topic),
            maxWidth = 0.7f
        )

        // a button to navigate to the Scoreboard screen
        AppDefaultButton(
            onButtonClicked = navigateToScoreResults,
            buttonText = stringResource(id = R.string.btn_scoreboard),
            maxWidth = 0.7f
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
    HomeScreen(
        navigateToChooseTopic = {},
        navigateToScoreResults = {},
    )
}