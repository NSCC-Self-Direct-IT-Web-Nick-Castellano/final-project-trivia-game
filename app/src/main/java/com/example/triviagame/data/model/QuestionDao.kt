package com.example.triviagame.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


/**
 * Database access object to access the Question database table
 */
@Dao
interface QuestionDao {
    // get all questions
    @Query("SELECT * FROM questions")
    fun getAllQuestions() : Flow<List<Question>>

    // get random question
    @Query("SELECT * FROM questions WHERE trivia_topic_id = :topicId ORDER BY RANDOM() LIMIT 1")
    fun getRandomQuestion(topicId : Long): Flow<Question>


    // insert a question
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question)

    // delete a question
    @Delete
    suspend fun delete(question: Question)

    // insert all the questions
    @Insert
    suspend fun insertInitialQuestions(questions : List<Question>)
}