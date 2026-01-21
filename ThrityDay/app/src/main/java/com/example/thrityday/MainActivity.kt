package com.example.thrityday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrityday.data.Datasource
import com.example.thrityday.data.FitnessTip
import com.example.thrityday.ui.theme.ThirtyDayTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ThirtyItemsList()
                }
            }
        }
    }
}

@Composable
fun ThirtyItemsList(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            ThirtyTopBar()
        }
    ) { it ->
        LazyColumn(
            contentPadding = it
        ) {
            items(Datasource.fitnessTips) { tip ->
                ThirtyItem(
                    tip,
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text("30 Days of Fitness")
        }
    )
}


@Composable
fun ThirtyItem(fitnessTip: FitnessTip, modifier: Modifier = Modifier) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    Card(modifier = modifier) {
        Column(modifier = Modifier
            .clickable(
                onClick = {
                    expanded = !expanded
                }
            )
            .padding(16.dp)
        ) {
            Text(
                text = "Day ${fitnessTip.number}",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(fitnessTip.title),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painterResource(fitnessTip.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                contentScale = ContentScale.Crop
            )
            if (expanded) {
                Text(
                    text = stringResource(fitnessTip.descriptRes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun ThirtyDayApp() {
    ThirtyDayTheme() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ThirtyItemsList()
        }
    }
}