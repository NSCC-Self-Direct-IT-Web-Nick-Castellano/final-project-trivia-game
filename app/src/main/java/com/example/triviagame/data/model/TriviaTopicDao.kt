package com.example.triviagame.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Trivia Topic database table
 */
@Dao
interface TriviaTopicDao {
    // get all trivia topics
    @Query("SELECT * FROM trivia_topics")
    fun getAllTriviaTopics() : Flow<List<TriviaTopic>>

    // get a single trivia topic
    @Query("SELECT * FROM trivia_topics WHERE id = :id")
    fun getSingleTriviaTopicById(id: Int) : Flow<TriviaTopic>

    // insert trivia topic
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(triviaTopic: TriviaTopic)

    // delete trivia topic
    @Delete
    suspend fun delete(triviaTopic: TriviaTopic)

    // insert initial topic
    @Insert
    suspend fun insertInitialTopics(topics : List<TriviaTopic>)

}