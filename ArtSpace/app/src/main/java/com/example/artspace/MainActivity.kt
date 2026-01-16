package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ImageAndDescriptionWithButton(modifier: Modifier = Modifier) {
    var counter: Int by remember { mutableStateOf(0) }
    val imgId = getImageIdWith(counter)
    var isNext: Boolean = false
    var isTurning: Boolean = false
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (isTurning) {
                            isTurning = false
                            if (isNext) {
                                counter++
                            } else {
                                counter--
                            }
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()

                        if (dragAmount > 50 && counter > 0) {
                            isNext = false
                            isTurning = true
                        } else if (dragAmount < -50 && counter < 4) {
                            isNext = true
                            isTurning = true
                        }
                    }
                )
            }
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageColumn(imgId = imgId, modifier.weight(1f).padding(32.dp))
        DescriptionAndButtons(value = counter, onClick = { newVal ->
            counter = newVal
        })
    }
}

@Composable
fun ImageColumn(
    @DrawableRes imgId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imgId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .wrapContentSize()
            .shadow(8.dp, RectangleShape)
            .border(34.dp, Color.White, RectangleShape)
            .padding(34.dp)
            .clip(RectangleShape)
    )
}

@Composable
fun DescriptionAndButtons(value: Int, modifier: Modifier = Modifier, onClick: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageDescriptions(counter = value)
        PrevNextButtons(value = value, onClick = onClick)
    }
}

@Composable
fun ImageDescriptions(counter: Int, modifier: Modifier = Modifier) {
    val title: String = stringResource(getTitleWith(counter))
    val descriptionText: String = getDescriptionTextWith(counter)
    val descriptionAnnotatedString = getAnnotatedString(descriptionText)
    Column(
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            textAlign = TextAlign.Start,
            modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
        Text(
            descriptionAnnotatedString,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun PrevNextButtons(value: Int, modifier: Modifier = Modifier, onClick: (Int) -> Unit) {
    var counter = value
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(start = 32.dp, bottom = 16.dp, end = 32.dp).fillMaxWidth()
    ) {
        Button(onClick = {
            if (counter > 0) {
                counter--
            }
            onClick(counter)
        }) {
            Text(
                "Previous",
                fontSize = 16.sp
                )
        }
        Spacer(modifier.width(100.dp))
        Button(
            onClick = {
                if (counter < 4) {
                    counter++
                }
                onClick(counter)
            }
        ) {
            Text(
                "Next",
                fontSize = 16.sp
            )
        }
    }
}

@DrawableRes
fun getImageIdWith(value: Int): Int {
    return when(value) {
        0 -> R.drawable.lilypad_toy_story_5_1284x2778_24874
        1 -> R.drawable.mario_and_luigi_1284x2778_24682
        2 -> R.drawable.neytiri_avatar_fire_1284x2778_24975
        3 -> R.drawable.nvidia_logo_green_1284x2778_24760
        else -> R.drawable.nvidia_processor_1284x2778_24764
    }
}

@StringRes
fun getTitleWith(counter: Int): Int {
    return when(counter) {
        0 -> R.string.lilipad
        1 -> R.string.mario
        2 -> R.string.avatar
        3 -> R.string.nvidia_logo
        else -> R.string.nvidia_processor
    }
}

fun getDescriptionTextWith(counter: Int): String {
    return when(counter) {
        0 -> "Netflix (2019)"
        1 -> "Sony (2017)"
        2 -> "Jame Cameron (2026)"
        3 -> "Jason Huang (2011)"
        else -> "Jonson Huang (2014)"
    }
}

fun getAnnotatedString(text: String): AnnotatedString {
    val artistText: String = getArtist(text)
    val publishedYearStr: String = getPublishedYear(text)
    val descriptionAnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(artistText)
        }
        append(publishedYearStr)
    }
    return descriptionAnnotatedString
}

fun getArtist(text: String): String {
    var artist: String = ""

    for (c in text) {
        if (c == '(') {
            break
        }
        artist += c
    }
    return artist
}

fun getPublishedYear(text: String): String {
    var yearStr: String = ""
    var isEnable: Boolean = false
    for (c in text) {
        if (c == '(') {
            isEnable = true
        }
        if (isEnable) {
            yearStr += c
        }
    }
    return yearStr
}

@Preview
@Composable
fun ArtSpaceApp() {
    ArtSpaceTheme {
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()) {
            ImageAndDescriptionWithButton()
        }
    }
}

