package com.example.assignment_ph36760.Model

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("_id") val id: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("fullname") val fullname: String
)

data class UserStatusResponse(
    val status: Int,
    val message: String,
    val id: String
)

data class LoginCredentials(
    val email: String,
    val password: String
)