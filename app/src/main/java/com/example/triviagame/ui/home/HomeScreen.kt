package com.example.triviagame.ui.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
        modifier = modifier,
    ) {
        /**
         * Add 2 buttons, one to navigate to ChooseTopic screen
         * and the other to navigate to ScoreResults screen
         */
        Text(
            text = stringResource(id = R.string.txt_welcome_screen),
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = navigateToChooseTopic,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stringResource(id = R.string.btn_choose_topic))
        }
        Button(
            onClick = navigateToScoreResults,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stringResource(id = R.string.btn_scoreboard))
        }
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