package com.example.assignment_ph36760.Response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment_ph36760.Service.RetrofitService
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserResponse {
//    fun userLogin(email: String, password: String) : LiveData<String>{
//
//        val loginResponse = MutableLiveData<String>()
//        RetrofitService().ApiService.userLogin(email, password).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if(response.isSuccessful){
//                    loginResponse.value = response.body()?.string()
//                }else{
//                    loginResponse.value = response.errorBody()?.string()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                loginResponse.value = t.message
//            }
//
//
//        })
//        return loginResponse
//    }
}