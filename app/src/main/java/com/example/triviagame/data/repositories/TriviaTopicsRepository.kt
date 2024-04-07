package com.example.finalprojectgeopardygameapp.data.repositories

import com.example.triviagame.data.model.TriviaTopic
import kotlinx.coroutines.flow.Flow


/**
 * Repository that provides insert, update, delete, and retrieve of [TriviaTopic] from a given data source.
 */
interface TriviaTopicsRepository {
    /**
     * Retrieve all the topics from the the given data source.
     */
    fun getAllTriviaTopics(): Flow<List<TriviaTopic>>

    /**
     * Retrieve a trivia topic from the given data source.
     */
    fun getSingleTriviaTopic(id: Int): Flow<TriviaTopic>

    /**
     * Insert topic in the data source
     */
    suspend fun insert(triviaTopic: TriviaTopic)


    /**
     * Delete topic from the data source
     */
    suspend fun delete(triviaTopic: TriviaTopic)

    /**
     * Insert a initial topics
     */
    suspend fun insertInitialTopics(topics: List<TriviaTopic>)

}
