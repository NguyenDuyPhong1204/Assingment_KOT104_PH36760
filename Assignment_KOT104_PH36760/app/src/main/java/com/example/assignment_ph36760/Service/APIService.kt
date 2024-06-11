package com.example.assignment_ph36760.Service

import com.example.assignment_ph36760.Model.Entity.Cart
import com.example.assignment_ph36760.Model.Entity.Favorite
import com.example.assignment_ph36760.Model.Entity.Product
import com.example.assignment_ph36760.Model.Entity.ProductType
import com.example.assignment_ph36760.Model.Entity.User
import com.example.assignment_ph36760.Model.UserStatusResponse
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.Response.UserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {

    @POST("register")
    suspend fun RegisterUser(@Body user: User): Response<UserResponse>


    @POST("login")
    suspend fun loginUser(@Body user: User): Response<ApiResponse<User>>
//    @POST("login")
//    fun userLogin(@Field("email") email: String,
//                  @Field("password") password: String,): Call<ResponseBody>

    @POST("add-cart")
    suspend fun  addCart(@Body cart: Cart)
    @POST("add-favorite")
    suspend fun  addFavorite(@Body favorite: Favorite)

    @GET("get-list-cart")
    suspend fun  getListCart(): Response<ApiResponse<List<Cart>>>

    @GET("get-list-favorite")
    suspend fun  getListFavorite(): Response<ApiResponse<List<Favorite>>>
    @GET("get-productType")
    suspend fun getProductType(): Response<ApiResponse<List<ProductType>>>
    @GET("get-product/{categoryID}")
    suspend fun getProductCategory(@Path("categoryID") categoryID: String): Response<ApiResponse<List<Product>>>

    @DELETE("delete-cart-by-id/{id}")
    suspend fun deleteCartById(@Path("id") id: String): Response<ApiResponse<Cart>>

    @DELETE("delete-favorite-by-id/{id}")
    suspend fun deleteFavoriteById(@Path("id") id: String): Response<ApiResponse<Favorite>>

    @DELETE("delete-cart")
    suspend fun deleteAllCart(): Response<ApiResponse<Cart>>
}