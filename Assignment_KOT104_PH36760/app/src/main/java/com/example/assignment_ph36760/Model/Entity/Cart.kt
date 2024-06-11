package com.example.assignment_ph36760.Model.Entity

class CartModel(

    var _id: String? = null, // MongoDB ObjectId
    var productId: String,
    var userId: String,
    var productName: String,
    var productImage: String,
    var quantity: Int,
    var productPrice: Double,
    )