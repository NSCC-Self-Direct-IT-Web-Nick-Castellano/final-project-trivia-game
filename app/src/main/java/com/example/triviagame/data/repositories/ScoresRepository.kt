package com.example.finalprojectgeopardygameapp.data.repositories

import com.example.triviagame.data.model.Score
import com.example.triviagame.data.model.ScoreWithTriviaTopicName
import kotlinx.coroutines.flow.Flow


/**
 * Repository that provides insert, update, delete, and retrieve of [Score] from a given data source.
 */
interface ScoresRepository {
    /**
     * Retrieve all the scores from the the given data source.
     */
    fun getAllScores(): Flow<List<Score>>

    /**
     * Retrieve a score from the given data source.
     */
    fun getSingleScore(id: Int): Flow<Score>

    /**
     * Insert score in the data source
     */
    suspend fun insert(score: Score)


    /**
     * Delete score from the data source
     */
    suspend fun delete(score: Score)


}
