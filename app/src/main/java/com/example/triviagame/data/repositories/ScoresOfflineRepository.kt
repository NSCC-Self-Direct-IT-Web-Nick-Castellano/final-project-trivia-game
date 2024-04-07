package com.example.finalprojectgeopardygameapp.data.repositories

import com.example.triviagame.data.model.Score
import com.example.triviagame.data.model.ScoreDao
import kotlinx.coroutines.flow.Flow

class ScoresOfflineRepository(private val scoreDao: ScoreDao) : ScoresRepository {
    override fun getAllScores(): Flow<List<Score>> = scoreDao.getAllScores()

    override fun getSingleScore(id: Int): Flow<Score> = scoreDao.getSingleScoreById(id)

    override suspend  fun insert(score: Score) = scoreDao.insert(score)

    override suspend  fun delete(score: Score) = scoreDao.delete(score)

}