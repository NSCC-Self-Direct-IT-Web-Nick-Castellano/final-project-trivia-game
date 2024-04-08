package com.example.finalprojectgeopardygameapp.data.repositories

import com.example.triviagame.data.model.Question
import kotlinx.coroutines.flow.Flow


/**
 * Repository that provides insert, update, delete, and retrieve of [Question] from a given data source.
 */
interface QuestionsRepository {
    /**
     * Retrieve all the questions from the the given data source.
     */
    fun getAllQuestions(): Flow<List<Question>>

    /**
     * Retrieve a random question from the given data source.
     */
    fun getRandomQuestion(topicId: Long): Flow<Question>

    /**
     * Insert initial questions
     */
    suspend fun insertInitialQuestions(questions: List<Question>)

    /**
     * Insert question in the data source
     */
    suspend fun insert(question: Question)


    /**
     * Delete question from the data source
     */
    suspend fun delete(question: Question)

}
