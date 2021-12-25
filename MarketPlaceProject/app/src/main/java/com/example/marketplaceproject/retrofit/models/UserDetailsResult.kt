package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

data class UserDetailsResult(
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: Int)

