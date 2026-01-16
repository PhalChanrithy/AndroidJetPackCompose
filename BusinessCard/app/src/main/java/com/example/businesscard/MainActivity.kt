package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {

            }
        }
    }
}

@Composable
fun AndroidImage() {
    val image = painterResource(R.drawable.android_logo)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(1 / 3F)
            .background(Color(20, 47, 64)),
    )
}

@Composable
fun AndroidVerticalBlock(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidImage()
        Text(
            "John Android",
            color = Color(20, 47, 64),
            fontSize = 50.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Text(
            "Android Developer Extraordinaire",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(43, 102, 56)
        )
    }
}

@Composable
fun InfoVerticalBlock(id: Int, text:String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier.width(100.dp))
        Icon(
            painterResource(id),
            contentDescription = null,
            modifier = Modifier,
            tint = Color(43, 102, 56)
        )
        Text(
            text,
            color = Color(43, 102, 56),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ContactUsVerticalBlock(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        InfoVerticalBlock(
            R.drawable.baseline_local_phone_24,
            "+885 (12) 345 678"
        )
        InfoVerticalBlock(
            R.drawable.baseline_share_24,
            text = "@Android Dev"
        )
        InfoVerticalBlock(
            R.drawable.baseline_email_24,
            text = "john_android@yahoo.com"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface(
        color = Color(214, 231, 214),
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidVerticalBlock()
        ContactUsVerticalBlock(Modifier.fillMaxWidth())
    }
}