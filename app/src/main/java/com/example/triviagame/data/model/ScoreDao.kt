package com.example.triviagame.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Score database table
 */
@Dao
interface ScoreDao {
    // get all scores
    @Query("SELECT * FROM scores")
    fun getAllScores() : Flow<List<Score>>

    // get single score
    @Query("SELECT * FROM scores WHERE id = :id")
    fun getSingleScoreById(id: Int) :  Flow<Score>

    // insert score
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Score)

    // delete score
    @Delete
    suspend fun delete(score: Score)



}