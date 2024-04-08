package com.example.triviagame.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.triviagame.TriviaGameApplication
import com.example.triviagame.ui.choosetopic.ChooseTopicViewModel
import com.example.triviagame.ui.game.GameTurnViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Trivia Game app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ChooseTopicViewModel(
                triviaTopicsRepository = triviaGameApplication().container.triviaTopicRepository
            )
        }
        initializer {
            val triviaTopicId = this.createSavedStateHandle().get("triviaTopicId") ?: "1"
            Log.d("AppViewModelProvider", "triviaTopicId: $triviaTopicId")

            GameTurnViewModel(
                questionsRepository = triviaGameApplication().container.questionRepository,
                scoresRepository = triviaGameApplication().container.scoresRepository,
                triviaTopicId = triviaTopicId.toLong()
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TriviaGameApplication].
 */
fun CreationExtras.triviaGameApplication(): TriviaGameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TriviaGameApplication)