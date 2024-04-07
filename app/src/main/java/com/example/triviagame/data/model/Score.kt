package com.example.triviagame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


// Score with properties id, userName, finalScore, and questionsAnswered.
@Entity(
    tableName = "scores",
    foreignKeys = [
        ForeignKey(
            entity = TriviaTopic::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("trivia_topic_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Score(
    @PrimaryKey
    val id : Long,

    @ColumnInfo(name = "user_name")
    val userName : String = "Default User",

    @ColumnInfo(name = "final_score")
    val finalScore : Int = 0,
    @ColumnInfo(name = "questions_answered")
    val questionsAnswered : Int = 0,


    @ColumnInfo(name = "trivia_topic_id")
    val triviaTopicId : Long
)
