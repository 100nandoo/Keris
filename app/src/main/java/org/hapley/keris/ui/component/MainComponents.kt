package org.hapley.keris.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import org.hapley.keris.R
import org.hapley.keris.ui.theme.OrangeHot

@Composable
fun TextPoint(text: String, isHot: Boolean) {
    val textColor = if (isHot) OrangeHot else MaterialTheme.colors.onSurface
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            color = textColor,
            text = text,
            style = MaterialTheme.typography.body1
        )

        if (isHot) {
            Image(
                painter = rememberCoilPainter(request = R.drawable.ic_hot),
                contentDescription = "Hot",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentPoint() {
    Column {
        TextPoint("1023", isHot = true)
        TextPoint("102", isHot = false)
    }
}