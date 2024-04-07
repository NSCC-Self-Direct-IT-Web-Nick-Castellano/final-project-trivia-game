package com.example.triviagame.data.datasources

import com.example.triviagame.data.model.TriviaTopic

/**
 * This method provides the initial topics table data
 */
fun getInitialTopics() : List<TriviaTopic> {
    return listOf<TriviaTopic>(
        TriviaTopic(id = 1, typeName = "Movies"),
        TriviaTopic(id = 2, typeName = "Videogames"),
        TriviaTopic(id = 3, typeName = "Music"),
        TriviaTopic(id = 4, typeName = "Sports"),
    )
}