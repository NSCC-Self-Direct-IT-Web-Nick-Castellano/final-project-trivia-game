package com.example.triviagame.ui.choosetopic

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
import com.example.triviagame.ui.components.AppDefaultTextLabel
import com.example.triviagame.ui.components.ChooseTopicButtonList
import com.example.triviagame.ui.navigation.NavigationDestination

/**
 * Choose Topic Screen Navigate Destination
 */
object ChooseTopicDestination : NavigationDestination {
    override val route = "choose_topic"
    override val titleRes = R.string.choose_topic_destination
}


@ExperimentalMaterial3Api
@Composable
fun ChooseTopicScreen(
//    navigateToChooseTopic: () -> Unit,
//    navigateToScoreResults: () -> Unit,
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TriviaGameTopAppBar(
                title = stringResource(
                    id = ChooseTopicDestination.titleRes
                ),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onNavigateBack
            )
        },
    ) { innerPadding ->
        ChooseTopicBody(
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
fun ChooseTopicBody(
//    navigateToChooseTopic: () -> Unit,
//    navigateToScoreResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {

        // a welcome message
        AppDefaultTextLabel(text = stringResource(id = R.string.txt_choose_topic))

        // choose topic button list
        ChooseTopicButtonList(navigateToChooseTopic = { /*TODO*/ })

    }
}


/**
 * Preview the HomeScreen
 */
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ChooseTopicScreenPreview() {
    ChooseTopicScreen(
//        navigateToChooseTopic = {},
//        navigateToScoreResults = {},
    )
}