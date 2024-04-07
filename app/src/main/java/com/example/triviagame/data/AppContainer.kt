package com.example.triviagame.data

import android.content.Context
import com.example.finalprojectgeopardygameapp.data.repositories.QuestionsRepository
import com.example.finalprojectgeopardygameapp.data.repositories.ScoresRepository
import com.example.finalprojectgeopardygameapp.data.repositories.TriviaTopicsRepository
import com.example.triviagame.data.repositories.offline.QuestionsOfflineRepository
import com.example.triviagame.data.repositories.offline.ScoresOfflineRepository
import com.example.triviagame.data.repositories.offline.TriviaTopicsOfflineRepository

interface AppContainer {
    val questionRepository: QuestionsRepository
    val scoreRepository: ScoresRepository
    val triviaTopicRepository: TriviaTopicsRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    private val database = TriviaGameDatabase.getDatabase(context)

    override val questionRepository: QuestionsRepository by lazy {
        QuestionsOfflineRepository(
            database.questionDao()
        )
    }

    override val scoreRepository: ScoresRepository by lazy {
        ScoresOfflineRepository(
            database.scoreDao()
        )
    }

    override val triviaTopicRepository: TriviaTopicsRepository by lazy {
        TriviaTopicsOfflineRepository(
            database.triviaTopicDao()
        )
    }

}