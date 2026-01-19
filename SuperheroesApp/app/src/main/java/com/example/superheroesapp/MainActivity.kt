package com.example.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.datasource.Datasource
import com.example.superheroesapp.hero.Hero
import com.example.superheroesapp.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroList()
                }
            }
        }
    }
}

@Composable
fun SuperheroList() {
    Scaffold(
        topBar = {SuperheroTopBar()},
        modifier = Modifier.statusBarsPadding()
    ) {
        LazyColumn(contentPadding = it) {
            items(Datasource.heroes) { hero ->
                SuperheroesItem(
                    hero,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroTopBar() {
    CenterAlignedTopAppBar(
        title = {Text(
            "SuperHeroes",
            style = MaterialTheme.typography.displayLarge
        )}
    )
}

@Composable
fun SuperheroesItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
            ) {
                Text(
                    text = stringResource(hero.name),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(hero.heroDescription),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            SuperheroIcon(hero.heroImage)
        }
    }
}

@Composable
fun SuperheroIcon(
    @DrawableRes imageResource: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageResource),
        contentDescription = null,
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Preview
@Composable
fun SuperheroesApp() {
    SuperheroesAppTheme() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            SuperheroList()
        }
    }
}


