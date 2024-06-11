package com.example.assignment_ph36760.Model

import androidx.annotation.DrawableRes

// class ProductType {
//    private var nameProductType: String? = null
//    @DrawableRes private var image: Int? = null
//
//    constructor(nameProductType: String?, image: Int?) {
//        this.nameProductType = nameProductType
//        this.image = image
//    }
//
//    constructor()
//}

data class ProductType(var nameProductType: String, @DrawableRes val image: Int)