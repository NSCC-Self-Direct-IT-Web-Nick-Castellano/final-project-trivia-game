package com.example.triviagame.ui.game

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectgeopardygameapp.data.repositories.QuestionsRepository
import com.example.finalprojectgeopardygameapp.data.repositories.ScoresRepository
import com.example.triviagame.data.datasources.getInitialQuestions
import com.example.triviagame.data.model.Question
import com.example.triviagame.data.model.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * ViewModel for the game turn screen, it has a single ui state variable that holds the current game turn state with the question, score and turn number
 */
class GameTurnViewModel (
    private val questionsRepository: QuestionsRepository,
    private val scoresRepository: ScoresRepository,
    private val triviaTopicId: Long,
): ViewModel() {


    // a constant to state the increase per good question
    private val SCORE_INCREMENT = 10
    private val TURN_INCREMENT = 1

//    make mutable state variables for the score, turn number and lose
    var score by mutableIntStateOf(0)
    var turn by mutableIntStateOf(1)
    var lose by mutableStateOf(false)



    // if it is null, populate the questions into the database
    init {
        viewModelScope.launch {
            Log.d("GameTurnViewModel", "triviaTopicId: $triviaTopicId")

            val randomQuestion : Question = questionsRepository.getRandomQuestion(triviaTopicId).first()


            Log.d("GameTurnViewModel", "randomQuestion: $randomQuestion")

            if ("$randomQuestion" === "null") {
                questionsRepository.insertInitialQuestions(getInitialQuestions())
            }
        }
    }

    var gameTurnUiState: StateFlow<GameTurnUiState> =
        questionsRepository.getRandomQuestion(triviaTopicId)
            .map {
                // use state
                GameTurnUiState(question = it, score = 0, turnNumber = 1, lose = false)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GameTurnUiState()
            )


    /**
     * Method to advance to next turn, it receives the question choice selected by the user and updates the score and turn number
     * as well as getting a new random question
     */
    fun nextTurn(selectedChoice: String){
        viewModelScope.launch {
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

                lose = true

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

            // update the state
            score = newScore
            turn = newTurnNumber


            Log.d("GameTurnViewModel", "New Score: $newScore")
            Log.d("GameTurnViewModel", "New Turn Number: $newTurnNumber")
        }
    }

    /** save the score and questions answered to the database using the score repository
     *
     */
    fun saveScore() {
        viewModelScope.launch {
            // make an instance of the score object
            val score = Score(
//                auto generate the id
                id = 0,
                userName = "Default User",
                finalScore = score,
                questionsAnswered = turn - 1,
                triviaTopicId = triviaTopicId
            )

            scoresRepository.insert(score)
        }
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
        correctAnswer = "correct answer",
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