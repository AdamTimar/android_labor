package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

data class UpdateUserResult(
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("phone_number")
    val phoneNumber: Int?)