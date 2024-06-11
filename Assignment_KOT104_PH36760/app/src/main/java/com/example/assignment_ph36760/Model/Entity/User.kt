package com.example.assignment_ph36760.Model.Entity

class User(
    val _id: String,
    val email: String,
    val password: String,
    val fullname: String
){
    constructor(email: String, password: String) : this("",email, password, "")
    constructor(email: String, password: String, fullname: String) : this("",email, password, fullname)
}