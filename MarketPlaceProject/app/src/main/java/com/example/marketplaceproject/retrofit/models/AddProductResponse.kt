package com.example.marketplaceproject.retrofit.models

import com.google.gson.annotations.SerializedName

data class AddProductResponse(
    @SerializedName("product_id")
    val productId : String,
    @SerializedName("username")
    val userName : String,
    @SerializedName("is_active")
    val isActive : Boolean,
    @SerializedName("price_per_unit")
    val pricePerUnit : String,
    @SerializedName("units")
    val units : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("amount_type")
    val amountType : String,
    @SerializedName("price_type")
    val priceType : String
)