package com.example.assignment_ph36760.Model.Entity

sealed class Screens(val screens: String) {
    data object Home: Screens("Home")
    data object Favorite: Screens("Favorite")
    data object Notification: Screens("Notification")
    data object Profile: Screens("Profile")
    data object Details: Screens("Detail")
    data object Welcome: Screens("Welcome")
    data object Login: Screens("Login")
    data object Register: Screens("Register")
    data object Cart: Screens("Cart")
    data object CheckOut: Screens("CheckOut")
    data object Congrats: Screens("Congrats")
    data object MainScreen: Screens("MainScreen")
}