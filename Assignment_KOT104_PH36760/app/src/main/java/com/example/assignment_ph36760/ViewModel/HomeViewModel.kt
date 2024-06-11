package com.example.assignment_ph36760.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_ph36760.Model.Entity.ProductType
import com.example.assignment_ph36760.Service.RetrofitService
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {
    private val _productType = MutableLiveData<List<ProductType>>()
    val productType: LiveData<List<ProductType>> = _productType

    init{
        getProductType()
    }

    private fun getProductType(){
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.getProductType()
                Log.d("PRDT", "getProductType: $response")
                if(response.isSuccessful){
                    _productType.value = response.body()
                }else{
                    Log.e("PRDT", "Error: ${response.errorBody().toString()}")
                }
            }catch (e: Exception){
                Log.e("PRDT", "Exception: ${e.message}", e)
            }
        }
    }
}