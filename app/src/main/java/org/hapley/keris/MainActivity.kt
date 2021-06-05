package org.hapley.keris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hapley.keris.ui.component.TextPoint
import org.hapley.keris.ui.theme.KerisTheme
import org.hapley.shared.network.KerisApi
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val binding by viewBinding(MainActivityBinding::inflate)

    @Inject
    lateinit var kerisApi: KerisApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KerisAppComposable()
        }
    }
}

@Composable
fun KerisAppComposable() {
    KerisTheme {
        val navController = rememberNavController()
        KerisNavHost(navController)
    }
}

object Route {
    const val LISTING = "listing"
    const val DETAIL = "detail"
}

@Composable
fun KerisNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Route.LISTING) {
        composable(Route.LISTING) { Story(navController = navHostController, uiStory = uiStory) }
        composable(Route.DETAIL) { Story(navController = navHostController, uiStory = uiStory) }
    }
}

val uiStory = UiStory(
    "333", true, "This is Title",
    "www.hapley.org", "123", false
)

@Composable
fun StoriesCard(uiStoryList: List<UiStory>) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(uiStoryList) { uiStory ->
//            Story(uiStory)
            Divider(color = Color.LightGray)
        }
    }
}

data class UiStory(
    val score: String, val isHot: Boolean,
    val title: String,
    val website: String,
    val commentScore: String, val commentHot: Boolean
)

@Composable
fun Story(navController: NavController, uiStory: UiStory) {
    var isSelected by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable(onClick = { isSelected = !isSelected })
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextPoint(uiStory.score, uiStory.isHot)
        Column(Modifier.weight(1f)) {
            Text(
                text = uiStory.title,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )

            Text(
                text = uiStory.website,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.overline
            )
        }
        TextPoint(uiStory.commentScore, uiStory.commentHot)
    }
}

@Preview(showBackground = true)
@Composable
fun StoryPreview() {
    val navController = rememberNavController()
    Story(navController, uiStory)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KerisTheme {
        StoriesCard(listOf(uiStory, uiStory, uiStory))
    }
}