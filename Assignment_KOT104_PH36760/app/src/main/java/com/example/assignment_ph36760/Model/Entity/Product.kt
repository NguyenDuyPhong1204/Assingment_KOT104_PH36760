package com.example.assignment_ph36760.Model

//class Product {
//    private var nameProduct: String? = null
//    private var imageProduct: String? = null
//    private var descriptionProduct: String? = null
//    private var priceProduct: Double? = null
//    private var evaluate: Double? = null
//    private var quantity: Int? = null
//
//
//    constructor()
//    constructor(
//        nameProduct: String?,
//        imageProduct: String?,
//        descriptionProduct: String?,
//        priceProduct: Double?,
//        evaluate: Double?,
//        quantity: Int?
//    ) {
//        this.nameProduct = nameProduct
//        this.imageProduct = imageProduct
//        this.descriptionProduct = descriptionProduct
//        this.priceProduct = priceProduct
//        this.evaluate = evaluate
//        this.quantity = quantity
//    }
//}
class Product(
    var nameProduct: String,
    var imageProduct: Int,
    var description: String,
    var price: Double,
    var evaluate: Double,
    var quantity: Int,
    var color: List<colorProduct>
)

class colorProduct(val nameColor: String)