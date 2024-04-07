package com.example.triviagame.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.triviagame.data.model.Question
import com.example.triviagame.data.model.QuestionDao
import com.example.triviagame.data.model.Score
import com.example.triviagame.data.model.ScoreDao
import com.example.triviagame.data.model.TriviaTopic
import com.example.triviagame.data.model.TriviaTopicDao

@Database(
    entities = [
        Question::class,
        Score::class,
        TriviaTopic::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TriviaGameDatabase : RoomDatabase(){
    abstract fun questionDao(): QuestionDao
    abstract fun scoreDao(): ScoreDao
    abstract fun triviaTopicDao(): TriviaTopicDao

    companion object {
        @Volatile
        private var Instance: TriviaGameDatabase? = null

        fun getDatabase(context: Context): TriviaGameDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TriviaGameDatabase::class.java,
                    "trivia_game_database"
                ).fallbackToDestructiveMigration().build()
                Instance = instance
                instance
            }
        }

//        /**
//         *  Populate the database with the initial data if it is empty
//         *  use datasources -> QuestionInitialData -> getInitialQuestions(), TriviaTopicInitialData -> getInitialTopics()
//         *  to get the initial data arrays and insert them into the database using room DAOs repositories methods
//         *  insertInitialQuestions and insertInitialTopics
//         */
//        fun populateDatabase(context: Context) {
//            val database = getDatabase(context)
//            val questionDao = database.questionDao()
//            val triviaTopicDao = database.triviaTopicDao()
//
//            // get all questions, remember that getAllQuestions() returns a Flow, we need to
//            // collect it and convert it to a list
//            if (questionDao.getAllQuestions().isEmpty()) {
//                // insert initial questions
//                questionDao.insertInitialQuestions(QuestionInitialData.getInitialQuestions())
//            }
//
//            if (triviaTopicDao.getAllTopics().isEmpty()) {
//                // insert initial topics
//                triviaTopicDao.insertInitialTopics(TriviaTopicInitialData.getInitialTopics())
//            }
//        }

    }
}