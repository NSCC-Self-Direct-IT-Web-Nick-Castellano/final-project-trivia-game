package com.example.triviagame.ui.choosetopic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectgeopardygameapp.data.repositories.TriviaTopicsRepository
import com.example.triviagame.data.datasources.getInitialTopics
import com.example.triviagame.data.model.TriviaTopic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChooseTopicViewModel (
    private val triviaTopicsRepository: TriviaTopicsRepository
) : ViewModel() {

    // make an init method that inserts the topics if the getAllTrivialTopics return empty
    init {
        viewModelScope.launch {
            val topic = triviaTopicsRepository.getAllTriviaTopics().first()

            if (topic.isEmpty()) {
                triviaTopicsRepository.insertInitialTopics(getInitialTopics())
            }
        }
    }

    // a ui state flow variable to store all the trivia topics
    val triviaTopicsUiState: StateFlow<ChooseTriviaTopicsUiState> = triviaTopicsRepository.getAllTriviaTopics()
        .map {
            ChooseTriviaTopicsUiState(triviaTopicsList = it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ChooseTriviaTopicsUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for the trivia topics
 */
data class ChooseTriviaTopicsUiState (
    val triviaTopicsList: List<TriviaTopic> = listOf()
)