package com.example.assignment_ph36760.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.Model.Screens

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screens.Welcome.screens) {
                composable(Screens.Welcome.screens){
                    WelcomeScreen(navController = navController)
                }

                composable(Screens.Login.screens){
                    LoginScreen(navController = navController)
                }

                composable(Screens.Register.screens){
                    ResgiterScreen(navController = navController)
                }
            }

        }
    }
}

//@Preview(showBackground = true)
@Composable
fun WelcomeScreen(navController: NavController) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "backgroundWelcom",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "MAKE YOUR",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.gelasio_variablefont_wght, FontWeight.Normal)),
                color = Color(0xFF606060),
                modifier = Modifier
                    .padding(start = 60.dp, top = 231.dp)
                    .width(166.dp),
                fontWeight = FontWeight(600),
                lineHeight = 30.sp
            )

            Text(
                text = "HOME BEAUTIFUL",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.gelasio_variablefont_wght, FontWeight.Normal)),
                color = Color(0xFF303030),
                modifier = Modifier
                    .padding(start = 60.dp, top = 10.dp)
                    .width(306.dp),
                fontWeight = FontWeight(700),
                lineHeight = 38.9.sp
            )

            Text(
                text = "The best simple place " +
                        "where you discover most wonderful furnitures " +
                        "and make your home beautiful",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunitosan, FontWeight.Normal)),
                color = Color(0xFF808080),
                modifier = Modifier
                    .padding(start = 80.dp, top = 35.dp)
                    .width(289.dp)
                    .height(105.dp),
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Justify,
                lineHeight = 35.sp
            )

            Spacer(modifier = Modifier.height(170.dp))



            Button(
                onClick = {
                    navController.navigate(Screens.Login.screens)
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(54.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = FontFamily(
                        Font(
                            R.font.gelasio_variablefont_wght,
                            FontWeight.Normal
                        )
                    ),
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp
                )
            }
        }
    }
}