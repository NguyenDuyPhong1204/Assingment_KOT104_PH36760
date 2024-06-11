package com.example.assignment_ph36760.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_ph36760.Model.Entity.Cart
import com.example.assignment_ph36760.Model.Entity.ProductType
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.Service.RetrofitService
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val _cart = MutableLiveData<ApiResponse<List<Cart>>?>()
    val cart: LiveData<ApiResponse<List<Cart>>?> = _cart


    init {
        getListCart()
    }

    private fun getListCart() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.getListCart()
                Log.d("CART", "getListCart: $response")
                if (response.isSuccessful) {
                    _cart.value = response.body()
                } else {
                    Log.e("CART", "getListCart: ${response.errorBody().toString()}")
                    _cart.value = null
                }
            } catch (e: Exception) {
                Log.e("CART", "getListCart: ${e.message}", e)
                _cart.value = null
            }
        }
    }

    fun addToCart(cart: Cart) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.addCart(cart)

                getListCart()
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the exception if needed
            }
        }
    }

      fun deleteCart(id: String){
       viewModelScope.launch {
           try {
               val response = RetrofitService().ApiService.deleteCartById(id)
               if(response.isSuccessful){
                   getListCart()
               }else{
                   false
               }
           }catch (e: Exception){
               Log.d("CART", "deleteMovieByIdErr: ${e.message}")
           }
       }
    }

    fun deleteAll(){
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.deleteAllCart()
                if(response.isSuccessful){
                    getListCart()
                }else{
                    false
                }
            }catch (e: Exception){
                Log.d("CART", "deleteMovieByIdErr: ${e.message}")
            }
        }
    }


    fun loadCart(){
        getListCart()
    }
}