package com.example.triviagame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Data class for Trivia Topic
 */
@Entity(tableName = "trivia_topics")
data class TriviaTopic (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "type_name")
    val typeName : String
)
