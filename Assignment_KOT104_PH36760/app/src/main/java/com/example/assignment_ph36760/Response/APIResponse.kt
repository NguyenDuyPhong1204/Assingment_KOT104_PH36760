package com.example.assignment_ph36760.Response

data class ApiResponse<T>(
    val status: Int,
    val message: String,
    var data: T
)