package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

data class PasswordResetModel(
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String)
