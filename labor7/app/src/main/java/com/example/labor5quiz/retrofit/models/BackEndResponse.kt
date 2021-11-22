package com.example.labor5quiz.retrofit.models

import com.google.gson.annotations.SerializedName

open class BackEndResponse<T>(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val results : T)
