package com.example.triviagame.data.repositories.offline

import com.example.finalprojectgeopardygameapp.data.repositories.ScoresRepository
import com.example.triviagame.data.model.Score
import com.example.triviagame.data.model.ScoreDao
import com.example.triviagame.data.model.ScoreWithTriviaTopicName
import com.example.triviagame.data.model.TriviaTopicDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScoresOfflineRepository(private val scoreDao: ScoreDao) : ScoresRepository {
    override fun getAllScores(): Flow<List<Score>> = scoreDao.getAllScores()

    override fun getSingleScore(id: Int): Flow<Score> = scoreDao.getSingleScoreById(id)

    override suspend fun insert(score: Score) = scoreDao.insert(score)

    override suspend fun delete(score: Score) = scoreDao.delete(score)


}