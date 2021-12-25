package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

open class UpdateUserBackEndResponse<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("updatedData")
    val result : T)