package com.example.assignment_ph36760.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_ph36760.Model.Entity.User
import com.example.assignment_ph36760.Model.UserStatusResponse
import com.example.assignment_ph36760.Response.UserResponse
import com.example.assignment_ph36760.Service.RetrofitService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _registerResponse = MutableSharedFlow<Response<UserResponse>>()
    val registerResponse: SharedFlow<Response<UserResponse>> = _registerResponse

    private val _registerError = MutableStateFlow<String?>(null)
    val registerError: StateFlow<String?> = _registerError

    fun register(email: String, password: String, fulllname: String, confirmPassword: String) {
        if (email.isEmpty() || password.isEmpty() || fulllname.isEmpty() || confirmPassword.isEmpty()) {
            if (email.isEmpty()) {
                _registerError.value = "Vui lòng nhập email"
            }
            if (password.isEmpty()) {
                _registerError.value = "Vui lòng nhập mật khẩu"
            }
            if (fulllname.isEmpty()) {
                _registerError.value = "Vui lòng nhập tên"
            }
            if (confirmPassword.isEmpty()) {
                _registerError.value = "Vui lòng nhập xác nhận mật khẩu"
            }
        } else if (!confirmPassword.equals(password)) {
            _registerError.value = "Mật khẩu và xác nhận mật khẩu phải trùng khớp"
        } else {
            viewModelScope.launch {
                val user = User(email, password, fulllname)
                val response = RetrofitService().ApiService.RegisterUser(user)
                if (response.isSuccessful) {
                    _registerResponse.emit(response)
                } else {
//                    _registerError.value = "Email hoặc mật khẩu không đúng"
                    Log.e("LOI", "Register: ")
                }
            }
        }
    }
}