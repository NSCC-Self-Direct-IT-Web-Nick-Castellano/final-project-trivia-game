package com.example.triviagame.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.triviagame.ui.choosetopic.ChooseTopicDestination
import com.example.triviagame.ui.choosetopic.ChooseTopicScreen
import com.example.triviagame.ui.game.GameTurnDestination
import com.example.triviagame.ui.game.GameTurnScreen
import com.example.triviagame.ui.home.HomeDestination
import com.example.triviagame.ui.home.HomeScreen
import com.example.triviagame.ui.scoreboard.ScoreboardDestination
import com.example.triviagame.ui.scoreboard.ScoreboardScreen


/**
 * Provides Navigation graph for the application.
 */
@ExperimentalMaterial3Api
@Composable
fun TriviaGameNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToChooseTopic = { },
                navigateToScoreResults = { }
            )
        }
        composable(route = ChooseTopicDestination.route) {
             ChooseTopicScreen()
        }
        composable(route = ScoreboardDestination.route) {
            ScoreboardScreen()
        }
        composable(route = GameTurnDestination.route) {
            GameTurnScreen()
        }
    }
}