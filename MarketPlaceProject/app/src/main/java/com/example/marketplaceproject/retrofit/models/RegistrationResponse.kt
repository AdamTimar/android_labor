package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String)

