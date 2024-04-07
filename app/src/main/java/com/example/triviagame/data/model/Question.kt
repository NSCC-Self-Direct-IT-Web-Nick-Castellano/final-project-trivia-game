package com.example.triviagame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


// Question with properties id, question, correctAnswer,
// and choices (a list containing options 'a', 'b', 'c', 'd').
/**
 * Entity data class represents a single row in the database.
 */
@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = TriviaTopic::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("trivia_topic_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id : Long,

    @ColumnInfo(name = "trivia_topic_id")
    val triviaTopicId: Long,


    val question : String,
    @ColumnInfo(name = "correct_answer")
    val correctAnswer : String,
    @ColumnInfo(name = "choice_a")
    val choiceA : String,
    @ColumnInfo(name = "choice_b")
    val choiceB : String,
    @ColumnInfo(name = "choice_c")
    val choiceC : String,
    @ColumnInfo(name = "choice_d")
    val choiceD : String
)

