package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

open class BackEndResponse<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val result : T)
