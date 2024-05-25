package com.example.assignment_ph36760.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.Model.Screens

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
//
            LoginScreen(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(97.dp)) // Chỉ sử dụng height cho khoảng cách phía trên
            Row(
                verticalAlignment = Alignment.CenterVertically, // Canh giữa theo chiều dọc
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center// Thêm padding bên trái cho toàn bộ hàng
            ) {
                Divider(
                    color = Color(0xFFBDBDBD),
                    thickness = 1.dp,
                    modifier = Modifier.width(105.dp) // Chiều dài của Divider
                )

                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = "Image",
                    modifier = Modifier.size(63.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))
                Divider(
                    color = Color(0xFFBDBDBD),
                    thickness = 1.dp,
                    modifier = Modifier.width(105.dp) // Chiều dài của Divider
                )


            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Hello !",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.merriweather_light, FontWeight.Normal)),
                color = Color(0xFF909090),
                modifier = Modifier
                    .padding(start = 40.dp, top = 10.dp)
                    .width(216.dp),
                fontWeight = FontWeight(400),
                lineHeight = 45.sp,

                )
            Text(
                text = "WELCOME BACK",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.merriweather_light, FontWeight.Normal)),
                color = Color(0xFF303030),
                modifier = Modifier
                    .padding(start = 40.dp, top = 10.dp)
                    .width(216.dp),
                lineHeight = 45.sp,
                fontWeight = FontWeight(700)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .width(345.dp)
                    .height(437.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(elevation = 3.dp, spotColor = Color(0xFF8A959E))
                    .padding(40.dp)
                    .background(color = Color.White)
            ) {
                TextStyle(
                    title = "Email",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF909090),
                    textAlign = null,
                    modifier = Modifier.height(25.dp).fillMaxWidth(),
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color(0xFFE0E0E0),
                        unfocusedIndicatorColor = Color(0xFFE0E0E0)
                    ),
                    modifier = Modifier.height(40.dp)
                )


                Spacer(modifier = Modifier.height(40.dp))
                TextStyle(
                    title = "Password",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF909090),
                    textAlign = null,
                    modifier = Modifier.height(25.dp).fillMaxWidth()
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color(0xFFE0E0E0),
                        unfocusedIndicatorColor = Color(0xFFE0E0E0)
                    ),
                    modifier = Modifier.height(40.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.eye),
                            contentDescription = "eye",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(25.dp))
                TextStyle(
                    title = "Forgot Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF303030),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.height(25.dp).fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(25.dp))
                Button(modifier = Modifier
                    .width(285.dp)
                    .height(50.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .shadow(elevation = 10.dp, spotColor = Color(0xFF303030)),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303030)),
                    onClick = {navController.navigate(Screens.Home.screens)}) {

                    TextStyle(
                        title = "Log in",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(25.dp).fillMaxWidth()
                    )

                }

                Spacer(modifier = Modifier.height(25.dp))
                TextStyle(
                    title = "SIGN UP",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF303030),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.height(25.dp).fillMaxWidth()
                        .clickable {
                            navController.navigate(Screens.Register.screens)
                        }
                )

            }
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
    modifier: Modifier
) {
    Text(
        text = title,
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = 19.sp,
        fontFamily = FontFamily(Font(R.font.nunitosan, FontWeight.Normal)),
        color = color,
        modifier = modifier,
//            .height(25.dp)
//            .fillMaxWidth(),
        textAlign = textAlign ?: null
    )
}
