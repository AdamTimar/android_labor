package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

data class PasswordResetResult(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String)
