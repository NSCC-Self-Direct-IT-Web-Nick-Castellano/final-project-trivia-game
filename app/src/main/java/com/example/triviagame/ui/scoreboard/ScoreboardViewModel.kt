package com.example.triviagame.ui.scoreboard

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectgeopardygameapp.data.repositories.ScoresRepository
import com.example.finalprojectgeopardygameapp.data.repositories.TriviaTopicsRepository
import com.example.triviagame.data.model.Score
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScoreboardViewModel(
     private val scoresRepository: ScoresRepository,
    private val triviaTopicsRepository: TriviaTopicsRepository
) : ViewModel() {

    private var triviaTopicsMap: Map<Long, String> = emptyMap()

    init {
        viewModelScope.launch {
            // Fetch all topics and create a map of IDs to names
            val allTopics = triviaTopicsRepository.getAllTriviaTopics().first()
            triviaTopicsMap = allTopics.associateBy({ it.id }, { it.typeName })
        }
    }

    /**
     * Get the trivia topic name for a given topic ID
     */
    suspend fun getTriviaTopicName(topicId: Long): String? {
        return triviaTopicsMap[topicId]
    }

    // initiate the ui state
    val scoreboardUiState: StateFlow<ScoreboardUiState> = scoresRepository.getAllScores()
        .map {
            // if the list is empty, return an empty list
            if (it.isEmpty()) {
                return@map ScoreboardUiState()
            }

            ScoreboardUiState(scoreboardList = it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ScoreboardUiState()
        )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


/**
 * Ui State for the scoreboard
 */
data class ScoreboardUiState (
    val scoreboardList: List<Score> = listOf()
)