package com.example.quadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quadrant.ui.theme.QuadrantTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuadrantTheme {
            }
        }
    }
}

@Composable
fun QuadText(title: String, description: String, hexColor: Long, modifier: Modifier = Modifier) {
    Surface(color = Color(hexColor)) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                description,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuadrantTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                horizontalArrangement = Arrangement.Absolute.spacedBy(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuadText(
                    stringResource(R.string.text_comp_title),
                    stringResource((R.string.text_comp_description)),
                    hexColor = 0xFFEADDFF,
                    modifier = Modifier.fillMaxWidth(0.5F)
                )
                QuadText(
                    stringResource(R.string.image_comp_title),
                    stringResource(R.string.image_comp_description),
                    hexColor = 0xFFD0BCFF,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuadText(
                    stringResource(R.string.row_comp_title),
                    stringResource(R.string.row_comp_description),
                    0xFFB69DF8,
                    modifier = Modifier.fillMaxWidth(0.5F)
                )
                QuadText(
                    stringResource(R.string.column_comp_title),
                    stringResource(R.string.column_comp_description),
                    0xFFF6EDFF,
                )
            }
        }
    }
}

//{
//    Row (
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
//    ) {
//        QuadText(
//            stringResource(R.string.text_comp_title),
//            stringResource(R.string.text_comp_description)
//        )
//        QuadText(
//            stringResource(R.string.image_comp_title),
//            stringResource(R.string.image_comp_description)
//        )
//    }
//    Row (
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        QuadText(
//            stringResource(R.string.row_comp_title),
//            stringResource(R.string.row_comp_description)
//        )
//        QuadText(
//            stringResource(R.string.column_comp_title),
//            stringResource(R.string.column_comp_description)
//        )
//    }
//}