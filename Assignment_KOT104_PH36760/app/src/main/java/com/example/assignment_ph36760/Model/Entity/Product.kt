package com.example.assignment_ph36760.Model.Entity


data class Product(
    var _id: String, // Tương ứng với ObjectId trong MongoDB
    var productName: String,
    var productImage: String,
    var productPrice: Float,
    var description: String,
    var quantity: Int,
    var evaluate: Float,
    var categoryID: String,
    var colors: List<Color>
) {
    // Nested class for Color

    constructor(
         productName: String,
         productImage: String,
         productPrice: Float,
         description: String,
         quantity: Int,
         evaluate: Float,
         categoryID: String,
         colors: List<Color>
    ):this(productName, productImage, productImage, productPrice, description, quantity, evaluate, categoryID, colors)

    data class Color(
        var colorName: String,
        var colorCode: String
    )
}

