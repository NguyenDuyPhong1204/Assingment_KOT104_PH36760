package com.example.assignment_ph36760.Model

sealed class Screens(val screens: String) {
    data object Home: Screens("Trang chủ")
    data object Favorite: Screens("Yêu thích")
    data object Notification: Screens("Thông báo")
    data object Profile: Screens("Hồ sơ")
    data object Details: Screens("Chi tiết")
    data object Welcome: Screens("Welcome")
    data object Login: Screens("Login")
    data object Register: Screens("Register")
    data object Cart: Screens("Cart")
}