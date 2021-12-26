package com.example.marketplaceproject.retrofit


import com.example.marketplaceproject.retrofit.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("user/register")
    fun register(@FieldMap params: Map<String,String>): Call<RegistrationResult>

    @POST("user/login")
    fun login(@Body loginCredentials: LoginCredentials): Call<LoginResult>

    @POST("user/reset")
    fun resetPassword(@Body passwordResetModel: PasswordResetModel): Call<PasswordResetResult>

    @GET("user/data")
    fun getUserDetails(@Header("username") userName: String): Call<BackEndResponse<List<UserDetailsResult>>>

    @FormUrlEncoded
    @POST("user/update")
    fun updateUser(@Header("token") token: String, @FieldMap params: Map<String,String>): Call<UpdateUserBackEndResponse<UpdateUserResult>>

    @GET("user/reset")
    fun resetPasswordWithToken(@Header("token") token: String, @Header("new_password") newPassword: String): Call<PasswordResetResult>

    @GET("products")
    fun getProducts(@Header("token") token: String, @Header("limit") limit: Int) : Call<ProductsResponse<List<ProductsResult>>>

    @FormUrlEncoded
    @POST("products/add")
    fun addProduct(@Header("token") token: String, @FieldMap params: Map<String,Any>) : Call<AddProductResponse>
}