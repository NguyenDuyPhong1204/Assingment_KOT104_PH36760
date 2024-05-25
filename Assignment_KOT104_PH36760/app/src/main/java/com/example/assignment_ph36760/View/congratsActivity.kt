package com.example.assignment_ph36760.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_ph36760.R

class congratsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CongratsScreen() {

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(124.dp))

        TextStyle(
            title = "SUCCESS!",
            fontSize = 36.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF303030),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.merriweather_black))
        )


        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(270.dp)
                    .height(270.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.background_congrat),
                    contentDescription = "background",
                    colorFilter = ColorFilter.tint(color = Color(0xFFEBEBEB))
                )


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        modifier = Modifier
                            .width(200.dp)
                            .height(180.dp),
                        painter = painterResource(id = R.drawable.imagesuccess),
                        contentDescription = "background",
                        colorFilter = ColorFilter.tint(color = Color(0xFF303030))
                    )

                }

                Image(
                    modifier = Modifier
                        .size(41.dp)
                        .align(alignment = Alignment.BottomCenter),
                    painter = painterResource(id = R.drawable.iconv),
                    contentDescription = "background",
                )

            }


        }

        Spacer(modifier = Modifier.height(20.dp))
        TextStyle(
            title = "Your order will be delivered soon.\n" +
                    "Thank you for choosing our app!",
            fontSize = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF606060),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.nunitosan))
        )

        Spacer(modifier = Modifier.height(35.dp))
        Button(
            modifier = Modifier
                .width(315.dp)
                .height(60.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .shadow(elevation = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
            onClick = {}) {

            TextStyle(
                title = "Track your orders",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily(Font(R.font.nunitosan))
            )
        }

        Spacer(modifier = Modifier.height(35.dp))
        Button(
            modifier = Modifier
                .width(315.dp)
                .height(60.dp)
                .align(alignment = Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(width = 1.dp, color = Color.Black),
            onClick = {}) {

            TextStyle(
                title = "BACK TO HOME",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily(Font(R.font.nunitosan))
            )
        }

    }
}

@Composable
private fun TextStyle(
    title: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color,
    textAlign: TextAlign?,
    modifier: Modifier,
    fontFamily: FontFamily
) {
    Text(
        text = title,
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = 30.sp,
        fontFamily = fontFamily,
        color = color,
        modifier = modifier,
        textAlign = textAlign ?: null
    )
}
