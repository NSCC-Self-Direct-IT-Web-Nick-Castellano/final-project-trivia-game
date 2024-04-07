package com.example.triviagame.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.triviagame.TriviaGameApplication

/**
 * Provides Factory to create instance of ViewModel for the entire Trivia Game app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

    }
}


/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TriviaGameApplication].
 */
fun CreationExtras.triviaGameApplication(): TriviaGameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TriviaGameApplication)