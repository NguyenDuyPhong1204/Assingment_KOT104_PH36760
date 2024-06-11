package com.example.assignment_ph36760.Model.Entity

class Cart(
    var _id: String? = null, // MongoDB ObjectId
    var productId: String,
    var productName: String,
    var productImage: String,
    var quantity: Int,
    var productPrice: Double,
    )