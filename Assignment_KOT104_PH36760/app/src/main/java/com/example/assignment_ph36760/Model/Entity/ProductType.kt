package com.example.assignment_ph36760.Model.Entity

import androidx.annotation.DrawableRes



data class ProductType(
    val _id: String,
    val title: String,
    val imageType: String
){
    constructor() : this("", "", "")
}
