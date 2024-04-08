package com.example.triviagame.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                navigateToChooseTopic = { navController.navigate(ChooseTopicDestination.route) },
                navigateToScoreResults = { navController.navigate(ScoreboardDestination.route) }
            )
        }
        composable(route = ChooseTopicDestination.route) {
             ChooseTopicScreen(
                onNavigateBack = { navController.navigateUp() },
                navigateToGameScreen = {triviaTopicId ->
                    navController.navigate("${GameTurnDestination.route}/$triviaTopicId")
                }
             )
        }
        composable(route = ScoreboardDestination.route) {
            ScoreboardScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = "${GameTurnDestination.route}/{triviaTopicId}",
        ) {backStackEntry ->
            val triviaTopicId = backStackEntry.arguments?.getString("triviaTopicId")
            GameTurnScreen(
                onNavigateBack = { navController.navigateUp() },
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateToStartOver = { triviaTopicId?.toLong()?.let { navController.navigate("${GameTurnDestination.route}/$it") } ?: navController.navigateUp() },
                triviaTopicId = triviaTopicId?.toLong() ?: 1
            )
        }
    }
}