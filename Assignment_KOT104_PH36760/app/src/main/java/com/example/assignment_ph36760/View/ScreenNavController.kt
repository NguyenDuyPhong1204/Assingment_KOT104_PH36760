package com.example.assignment_ph36760.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment_ph36760.Model.Entity.Product
import com.example.assignment_ph36760.Model.Entity.Screens
import com.example.assignment_ph36760.ViewModel.CartViewModel
import com.example.assignment_ph36760.ViewModel.HomeViewModel
import com.example.assignment_ph36760.ViewModel.LoginViewModel
import com.example.assignment_ph36760.ViewModel.RegisterViewModel
import com.google.gson.Gson

class ScreenNavController : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenNavigation()
        }
    }
}

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val loginViewModel = LoginViewModel()
    val registerViewModel = RegisterViewModel()
    val homeViewModel = HomeViewModel()
    val cartViewModel = CartViewModel()
    NavHost(navController = navController, startDestination = Screens.Welcome.screens) {
        composable(Screens.Welcome.screens) {
            WelcomeScreen(navController = navController)
        }

        composable(Screens.Login.screens) {
            LoginScreen(navController = navController, loginViewModel)
        }


        composable(Screens.Register.screens) {
            ResgiterScreen(navController = navController, registerViewModel)
        }

        composable(Screens.MainScreen.screens) {
           MyBottomAppBar()
        }

        composable(Screens.Home.screens){
            HomeScreen(homeViewModel = homeViewModel, navController = navController)
        }
        
        composable(Screens.Cart.screens){
            CartScreen(cartViewModel, navController)
        }

        composable(Screens.Congrats.screens){
            CongratsScreen()
        }
        
        composable(Screens.CheckOut.screens){
            CheckoutScreen(navController = navController)
        }

////        composable(
////            Screens.Details.screens+"/id/name/image/description/price/evaluate/quantity/color",
////            arguments = listOf(
////                navArgument("id",{type = NavType.StringType}),
////                navArgument("name", { type = NavType.StringType }),
////                navArgument("image") { type = NavType.StringType },
////                navArgument("description") { type = NavType.StringType },
////                navArgument("price") { type = NavType.FloatType },
////                navArgument("evaluate") { type = NavType.FloatType },
////                navArgument("quantity") { type = NavType.IntType },
////                navArgument("color") { type = NavType.StringType }
////            )
////        ) {backStackEntry ->
////            val id = backStackEntry.arguments?.getString("id") ?: ""
////            val name = backStackEntry.arguments?.getString("name") ?: ""
////            val image = backStackEntry.arguments?.getString("image") ?: ""
////            val description = backStackEntry.arguments?.getString("description") ?: ""
////            val price = backStackEntry.arguments?.getFloat("price") ?: 0.0f
////            val evaluate = backStackEntry.arguments?.getFloat("evaluate") ?: 0.0f
////            val quantity = backStackEntry.arguments?.getInt("quantity") ?: 0
////            val colorJson = backStackEntry.arguments?.getString("color") ?: "[]"
////            val colorList = Gson().fromJson(colorJson, Array<Product.Color>::class.java).toList()
////
////            DetailsScreen(navController, id,name,image,description,price,evaluate,quantity,colorList)
//        }
    }
}