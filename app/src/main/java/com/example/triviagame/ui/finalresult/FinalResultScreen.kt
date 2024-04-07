package com.example.triviagame.ui.finalresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.triviagame.ui.components.FinalResultContainer
import com.example.triviagame.ui.navigation.NavigationDestination

/**
 * Choose Topic Screen Navigate Destination
 */
object FinalResultDestination : NavigationDestination {
    override val route = "final_result"
    override val titleRes = R.string.final_result_destination
}


@ExperimentalMaterial3Api
@Composable
fun FinalResultScreen(
//    navigateToChooseTopic: () -> Unit,
//    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            TriviaGameTopAppBar(
                title = stringResource(
                    id = FinalResultDestination.titleRes
                ),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        FinalResultBody(
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
fun FinalResultBody(
//    navigateToChooseTopic: () -> Unit,
//    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
    ) {

        FinalResultContainer()


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


/**
 * Preview the HomeScreen
 */
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun FinalResultScreenPreview() {
    FinalResultScreen(
//        navigateToChooseTopic = {},
//        navigateToScoreResults = {},
    )
}