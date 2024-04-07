package com.example.finalprojectgeopardygameapp.data.repositories

import com.example.triviagame.data.model.Question
import com.example.triviagame.data.model.QuestionDao
import kotlinx.coroutines.flow.Flow


class QuestionsOfflineRepository(private val questionDao: QuestionDao) : QuestionsRepository {
    override fun getAllQuestions(): Flow<List<Question>> = questionDao.getAllQuestions()

    override fun getRandomQuestion(): Flow<Question> = questionDao.getRandomQuestion()

    override suspend  fun insert(question: Question) = questionDao.insert(question = question)

    override suspend  fun delete(question: Question) = questionDao.delete(question = question)

}
