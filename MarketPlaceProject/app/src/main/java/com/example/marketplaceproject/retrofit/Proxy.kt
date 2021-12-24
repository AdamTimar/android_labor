package com.example.marketplaceproject.retrofit

import com.example.marketplaceproject.retrofit.models.LoginCredentials
import com.example.marketplaceproject.retrofit.models.PasswordResetModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Proxy {

    private const val BASE_URL = "https://pure-gorge-51703.herokuapp.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    private val service: RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }

    fun register(email: String, userName: String, password: String) = service.register(mapOf("username" to userName, "email" to email, "password" to password)).execute().body()
    fun login(userName: String, password:String) = service.login(LoginCredentials(userName, password)).execute().body()
    fun resetPassword(userName: String, email: String) = service.resetPassword(PasswordResetModel(userName,email)).execute().body()
}
