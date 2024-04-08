package com.example.triviagame.ui.game

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectgeopardygameapp.data.repositories.QuestionsRepository
import com.example.triviagame.data.datasources.getInitialQuestions
import com.example.triviagame.data.model.Question
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * ViewModel for the game turn screen, it has a single ui state variable that holds the current game turn state with the question, score and turn number
 */
class GameTurnViewModel (
    private val questionsRepository: QuestionsRepository,
    private val triviaTopicId: Long,
): ViewModel() {


    // a constant to state the increase per good question
    private val SCORE_INCREMENT = 10
    private val TURN_INCREMENT = 1

    lateinit var gameTurnUiState : StateFlow<GameTurnUiState>


    // if it is null, populate the questions into the database
    init {
        viewModelScope.launch {
            Log.d("GameTurnViewModel", "triviaTopicId: $triviaTopicId")
            val randomQuestion = questionsRepository.getRandomQuestion(triviaTopicId)

            if (randomQuestion == null) {
                questionsRepository.insertInitialQuestions(getInitialQuestions())
            }

            gameTurnUiState = questionsRepository.getRandomQuestion(triviaTopicId)
            .map {
                // use state
                GameTurnUiState(question = it, score = 0, turnNumber = 1, lose = false)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GameTurnUiState()
            )
        }
    }


    /**
     * Method to advance to next turn, it receives the question choice selected by the user and updates the score and turn number
     * as well as getting a new random question
     */
    fun nextTurn(selectedChoice: String){
        val currentScore = gameTurnUiState.value.score
        val currentTurnNumber = gameTurnUiState.value.turnNumber
        val currentQuestion = gameTurnUiState.value.question

        var newScore: Int = currentScore
        var newTurnNumber: Int = currentTurnNumber

        Log.d("GameTurnViewModel", "Question: ${currentQuestion.question}")
        Log.d("GameTurnViewModel", "Selected Choice: $selectedChoice")
        Log.d("GameTurnViewModel", "Correct Answer: ${currentQuestion.correctAnswer}")


        if (selectedChoice != currentQuestion.correctAnswer) {
            Log.d("GameTurnViewModel", "Wrong Answer")
//            lose = true

            // modify the lose variable to true, keep the rest the same, also keep the same
            // random question
            gameTurnUiState = questionsRepository.getRandomQuestion(triviaTopicId)
                .map {
                    GameTurnUiState(question = it, score = newScore, turnNumber = newTurnNumber, lose = true)
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = GameTurnUiState(
                        question = currentQuestion,
                        score = newScore,
                        turnNumber = newTurnNumber,
                        lose = true
                    )
                )


        } else {
            Log.d("GameTurnViewModel", "Correct Answer")
            newScore = newScore + SCORE_INCREMENT
            newTurnNumber = newTurnNumber + TURN_INCREMENT
            // update the state
//            score = newScore
//            turn = newTurnNumber

            gameTurnUiState = questionsRepository.getRandomQuestion(triviaTopicId)
                .map {
                    GameTurnUiState(question = it, score = newScore, turnNumber = newTurnNumber)
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = GameTurnUiState(
                        question = currentQuestion,
                        score = newScore,
                        turnNumber = newTurnNumber,
                        lose = false
                    )
                )
        }







        Log.d("GameTurnViewModel", "New Score: $newScore")
        Log.d("GameTurnViewModel", "New Turn Number: $newTurnNumber")
    }



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}

/**
 * Ui State for the game turn, it contains a single trivia question object, the score, the turn number
 */
data class GameTurnUiState (
    val question: Question = Question(
        id = 1,
        question = "question",
        correctAnswer = "correct aswer",
        triviaTopicId = 1,
        choiceA = "choice",
        choiceB = "choice",
        choiceC = "choice",
        choiceD = "choice"
    ),
    val score: Int = 0,
    val turnNumber: Int = 1,
    val lose: Boolean = false
)