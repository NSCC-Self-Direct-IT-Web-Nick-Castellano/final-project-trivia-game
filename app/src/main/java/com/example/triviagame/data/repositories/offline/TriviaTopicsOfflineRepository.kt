package com.example.triviagame.data.repositories.offline

import com.example.finalprojectgeopardygameapp.data.repositories.TriviaTopicsRepository
import com.example.triviagame.data.model.TriviaTopic
import com.example.triviagame.data.model.TriviaTopicDao
import kotlinx.coroutines.flow.Flow


class TriviaTopicsOfflineRepository(
    private val triviaTopicDao: TriviaTopicDao
) : TriviaTopicsRepository {
    override fun getAllTriviaTopics(): Flow<List<TriviaTopic>> {
        return triviaTopicDao.getAllTriviaTopics()
    }

    override fun getSingleTriviaTopic(id: Int): Flow<TriviaTopic> {
        return triviaTopicDao.getSingleTriviaTopicById(id)
    }

    override suspend  fun insert(triviaTopic: TriviaTopic) {
        triviaTopicDao.insert(triviaTopic = triviaTopic)
    }

    override suspend  fun delete(triviaTopic: TriviaTopic) {
        triviaTopicDao.delete(triviaTopic = triviaTopic)
    }

    override suspend fun insertInitialTopics(topics: List<TriviaTopic>) {
        triviaTopicDao.insertInitialTopics(topics = topics)
    }
}