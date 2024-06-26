package com.example.triviagame.data.repositories.offline

import com.example.finalprojectgeopardygameapp.data.repositories.QuestionsRepository
import com.example.triviagame.data.model.Question
import com.example.triviagame.data.model.QuestionDao
import kotlinx.coroutines.flow.Flow


class QuestionsOfflineRepository(private val questionDao: QuestionDao) : QuestionsRepository {
    override fun getAllQuestions(): Flow<List<Question>> = questionDao.getAllQuestions()

    override fun getRandomQuestion(topicId: Long): Flow<Question> = questionDao.getRandomQuestion(
        topicId = topicId
    )

    override suspend  fun insert(question: Question) = questionDao.insert(question = question)

    override suspend  fun delete(question: Question) = questionDao.delete(question = question)

    override suspend fun insertInitialQuestions(questions: List<Question>) {
        questionDao.insertInitialQuestions(questions = questions)
    }

}
