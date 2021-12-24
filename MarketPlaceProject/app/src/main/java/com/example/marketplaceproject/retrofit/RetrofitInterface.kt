package com.example.marketplaceproject.retrofit


import com.example.marketplaceproject.retrofit.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("user/register")
    fun register(@FieldMap params: Map<String,String>): Call<RegistrationResponse>

    @POST("user/login")
    fun login(@Body loginCredentials: LoginCredentials): Call<LoginResponse>

    @POST("user/reset")
    fun resetPassword(@Body passwordResetModel: PasswordResetModel): Call<PasswordResetResponse>

}