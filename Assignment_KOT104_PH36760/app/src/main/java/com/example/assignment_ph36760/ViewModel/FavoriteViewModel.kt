package com.example.assignment_ph36760.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_ph36760.Model.Entity.Cart
import com.example.assignment_ph36760.Model.Entity.Favorite
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.Service.RetrofitService
import kotlinx.coroutines.launch

class FavoriteViewModel:ViewModel() {
    private val _favorite = MutableLiveData<ApiResponse<List<Favorite>>?>()
    val favorite: LiveData<ApiResponse<List<Favorite>>?> = _favorite
    init {
        getListFavorite()
    }
    private fun getListFavorite() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.getListFavorite()
                Log.d("CART", "getListCart: $response")
                if (response.isSuccessful) {
                    _favorite.value = response.body()
                } else {
                    Log.e("CART", "getListCart: ${response.errorBody().toString()}")
                    _favorite.value = null
                }
            } catch (e: Exception) {
                Log.e("CART", "getListCart: ${e.message}", e)
                _favorite.value = null
            }
        }
    }
    fun addToFavorite(favorite: Favorite) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.addFavorite(favorite)
                getListFavorite()
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the exception if needed
            }
        }
    }

    fun deleteFavorite(id: String){
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.deleteFavoriteById(id)
                if(response.isSuccessful){
                    getListFavorite()
                }else{
                    false
                }
            }catch (e: Exception){
                Log.d("CART", "deleteMovieByIdErr: ${e.message}")
            }
        }
    }

    fun loadFavorite(){
        getListFavorite()
    }
}