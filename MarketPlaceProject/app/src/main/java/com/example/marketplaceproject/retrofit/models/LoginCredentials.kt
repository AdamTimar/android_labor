package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

class LoginCredentials(
    @SerializedName("username")
    val userName: String,
    @SerializedName("password")
    val password: String)