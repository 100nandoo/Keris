package org.hapley.keris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.hapley.keris.ui.theme.KerisTheme
import org.hapley.shared.network.KerisApi
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var kerisApi: KerisApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val response = kerisApi.getTopStories().map { it.toString() }
            setContent {
                KerisTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        StoriesCard(response)
                    }
                }
            }
        }
    }
}

@Composable
fun StoriesCard(idList: List<String>) {
    LazyColumn {
        items(idList) { id ->
            Story(id)
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun Story(text: String) {
    var isSelected by remember { mutableStateOf(false) }
    val textColor by animateColorAsState(if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary)

    Row(
        modifier = Modifier
            .clickable(onClick = { isSelected = !isSelected })
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = textColor)) {
                    append(text)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KerisTheme {
        StoriesCard(listOf("1", "2", "3"))
    }
}