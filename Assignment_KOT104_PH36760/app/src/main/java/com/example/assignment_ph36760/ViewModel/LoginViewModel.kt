package com.example.assignment_ph36760.ViewModel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.assignment_ph36760.Model.AuthListener
import com.example.assignment_ph36760.Model.Entity.User
import com.example.assignment_ph36760.Model.LoginCredentials
import com.example.assignment_ph36760.Model.UserRequest
import com.example.assignment_ph36760.Model.UserStatusResponse
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.Response.UserResponse
import com.example.assignment_ph36760.Service.RetrofitService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResponse = MutableSharedFlow<Response<ApiResponse<User>>>()
    val loginResponse: SharedFlow<Response<ApiResponse<User>>> = _loginResponse

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            if (email.isEmpty()) {
                _loginError.value = "Vui lòng nhập email"
            }
            if (password.isEmpty()) {
                _loginError.value = "Vui lòng nhập mật khẩu"
            }
        } else {
            viewModelScope.launch {
                val user = User(email, password)
                val response = RetrofitService().ApiService.loginUser(user)
                if (response.isSuccessful) {
                    _loginResponse.emit(response)
                    val userResponse = response.body()
                    val userID = userResponse?.data?._id
                    Log.d("USER", "login: ${userResponse}")
                    if (userID != null) {
                        Log.d("USERID", "login: $userID")

                        // Ở đây, userID là ID của người dùng khi họ đăng nhập thành công
                    } else {
                        Log.e("USERID", "User ID is null")
                    }
                } else {
                    _loginError.value = "Email hoặc mật khẩu không đúng"
                    Log.e("LOI", "Login: ")
                }
            }
        }
    }


}
