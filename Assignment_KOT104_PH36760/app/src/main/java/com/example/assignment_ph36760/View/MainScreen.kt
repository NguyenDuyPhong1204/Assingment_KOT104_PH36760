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
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment_ph36760.Model.Entity.Product
import com.example.assignment_ph36760.Model.Entity.Screens
import com.example.assignment_ph36760.ViewModel.CartViewModel
import com.example.assignment_ph36760.ViewModel.FavoriteViewModel
import com.example.assignment_ph36760.ViewModel.HomeViewModel
import com.google.gson.Gson

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
    val homeViewModel = HomeViewModel()
    val cartViewModel = CartViewModel()
    val favoriteViewModel = FavoriteViewModel()
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
                        navController.navigate(Screens.Favorite.screens) {
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
                        selected.value = Icons.Default.ShoppingCart
                        navController.navigate(Screens.Cart.screens) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected.value == Icons.Default.ShoppingCart) Color.DarkGray else Color(
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
            navController = navController,
            startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
        )
        {
            composable(Screens.Home.screens) { HomeScreen(homeViewModel, navController) }
            composable(Screens.Favorite.screens) { FavoriteScreen(favoriteViewModel = favoriteViewModel) }
            composable(Screens.Cart.screens) { CartScreen(cartViewModel = cartViewModel, navController) }
            composable(Screens.Profile.screens) { ProfileScreen() }
//            composable(
//                Screens.Details.screens+"/id/name/image/description/price/evaluate/quantity/color",
//                arguments = listOf(
//                    navArgument("id",{type = NavType.StringType}),
//                    navArgument("name", { type = NavType.StringType }),
//                    navArgument("image") { type = NavType.StringType },
//                    navArgument("description") { type = NavType.StringType },
//                    navArgument("price") { type = NavType.FloatType },
//                    navArgument("evaluate") { type = NavType.FloatType },
//                    navArgument("quantity") { type = NavType.IntType },
//                    navArgument("color") { type = NavType.StringType }
//                )
//            ) {navBackStackEntry ->
//                val id = navBackStackEntry.arguments?.getString("id") ?: ""
//                val name = navBackStackEntry.arguments?.getString("name") ?: ""
//                val image = navBackStackEntry.arguments?.getString("image") ?: ""
//                val description = navBackStackEntry.arguments?.getString("description") ?: ""
//                val price = navBackStackEntry.arguments?.getFloat("price") ?: 0.0f
//                val evaluate = navBackStackEntry.arguments?.getFloat("evaluate") ?: 0.0f
//                val quantity = navBackStackEntry.arguments?.getInt("quantity") ?: 0
//                val colorJson = navBackStackEntry.arguments?.getString("color") ?: "[]"
//                val colorList = Gson().fromJson(colorJson, Array<Product.Color>::class.java).toList()
//
//                DetailsScreen(navController, id,name,image,description,price,evaluate,quantity,colorList)
//            }
        }
    }
}

@Composable
fun MainScreenBar(navController: NavController) {
    MyBottomAppBar()
}

@Preview
@Composable
fun PreviewUIMain() {
    val navController = rememberNavController()
    MyBottomAppBar()
}