package com.example.assignment_ph36760.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment_ph36760.Model.Screens

class MainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainScreenBar(navController = navController)
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MyBottomAppBar() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Screens.Home.screens) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.DarkGray else Color(
                            0xFF999999
                        )
                    )
                }
                //favorite
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.FavoriteBorder
                        navController.navigate(Screens.Cart.screens) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected.value == Icons.Default.FavoriteBorder) Color.DarkGray else Color(
                            0xFF999999
                        )
                    )
                }
                //cart
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Notifications
                        navController.navigate(Screens.Notification.screens) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected.value == Icons.Default.Notifications) Color.DarkGray else Color(
                            0xFF999999
                        )
                    )
                }
                //profile
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navController.navigate(Screens.Profile.screens) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected.value == Icons.Default.Person) Color.DarkGray else Color(
                            0xFF999999
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController, startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
        )
        {
            composable(Screens.Home.screens) { HomeScreen() }
            composable(Screens.Cart.screens) { CartScreen() }
            composable(Screens.Notification.screens) { NotificationScreen() }
            composable(Screens.Profile.screens) { ProfileScreen() }
        }
    }
}

@Composable
fun MainScreenBar(navController: NavController){
    MyBottomAppBar()
}

@Preview
@Composable
fun PreviewUIMain() {
    val navController = rememberNavController()
    MyBottomAppBar()
}