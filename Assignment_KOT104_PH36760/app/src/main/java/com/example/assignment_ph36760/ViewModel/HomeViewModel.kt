package com.example.assignment_ph36760.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_ph36760.Model.Entity.Product
import com.example.assignment_ph36760.Model.Entity.ProductType
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.Service.RetrofitService
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _productType = MutableLiveData<ApiResponse<List<ProductType>>?>()
    val productType: LiveData<ApiResponse<List<ProductType>>?> = _productType

    private val _product = MutableLiveData<ApiResponse<List<Product>>?>()
    val product: LiveData<ApiResponse<List<Product>>?> = _product

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
                    _productType.value = null
                }
            }catch (e: Exception){
                Log.e("PRDT", "Exception: ${e.message}", e)
                _productType.value = null
            }
        }
    }

    private fun getProduct(typeID: String){
        viewModelScope.launch {
            try {
                val response = RetrofitService().ApiService.getProductCategory(typeID)
                Log.d("PRD", "getProduct: $response")
                if(response.isSuccessful){
                    _product.value = response.body()
                }else{
                    Log.e("PRD", "getProduct: ${response.errorBody().toString()}")
                    _product.value = null
                }
            }catch (e: Exception){
                Log.e("PRD", "getProduct: ${e.message}", e )
                _product.value = null
            }
        }
    }
    fun getProductByType(productTypeId: String) {
        getProduct(productTypeId)
    }

}