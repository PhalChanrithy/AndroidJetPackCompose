package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTogetherTheme {
                Column {
                    HeaderImage()
                    LearningText(
                        stringResource(R.string.tutorial_header),
                        24.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    LearningText(
                        stringResource(R.string.jetpack_compose_def),
                        16.sp,
                        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)
                    )
                    LearningText(
                        stringResource(R.string.tutorial_purpose),
                        16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderImage() {
    val image = painterResource(R.drawable.learn_together_header)
    Image(
        painter = image,
        contentDescription = null
    )
}

@Composable
fun LearningText(message: String, size: TextUnit, modifier: Modifier = Modifier) {
    Text(
        text = message,
        fontSize = size,
        textAlign = TextAlign.Justify,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnTogetherTheme {
        Column {
            HeaderImage()
            LearningText(
                stringResource(R.string.tutorial_header),
                24.sp,
                modifier = Modifier.padding(16.dp)
            )
            LearningText(
                stringResource(R.string.jetpack_compose_def),
                16.sp,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)
            )
            LearningText(
                stringResource(R.string.tutorial_purpose),
                16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}