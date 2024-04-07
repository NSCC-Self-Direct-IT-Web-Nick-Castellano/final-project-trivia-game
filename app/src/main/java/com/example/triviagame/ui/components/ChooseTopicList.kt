package com.example.triviagame.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.triviagame.data.model.TriviaTopic


/**
 * Choose topic button list
 */
@Composable
fun ChooseTopicButtonList(
    navigateToChooseTopic: () -> Unit,
    topics: List<TriviaTopic>,
    modifier: Modifier = Modifier
) {
//    // make a list of TriviaTopic
//    val triviaTopicList = listOf<TriviaTopic>(
//        TriviaTopic(id = 1, typeName = "Movies"),
//        TriviaTopic(id = 2, typeName = "Music"),
//        TriviaTopic(id = 3, typeName = "Videogane"),
//        TriviaTopic(id = 4, typeName = "Sports"),
//    )

    if (topics.isEmpty()) {
        // if the list is empty, show a message
        AppDefaultTextLabel(
            text = "No topics available",
            modifier = modifier
        )
    } else {
        LazyColumn {
            items(items = topics) { item ->
                AppDefaultButton(
                    onButtonClicked = navigateToChooseTopic,
                    buttonText = item.typeName,
                    maxWidth = 0.7f
                )
            }
        }
    }
}

/**
 * Choose topic list preview
 */
@Preview(showBackground = true)
@Composable
fun ChooseTopicButtonListPreview() {
    ChooseTopicButtonList(
        navigateToChooseTopic = {},
        topics = listOf(
            TriviaTopic(id = 1, typeName = "Movies"),
            TriviaTopic(id = 2, typeName = "Music"),
            TriviaTopic(id = 3, typeName = "Videogane"),
            TriviaTopic(id = 4, typeName = "Sports"),
        )
    )
}