package com.example.marketplaceproject.retrofit.accesLayers

import android.util.Log
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.retrofit.Proxy
import com.example.marketplaceproject.retrofit.models.AddProductResponse
import io.reactivex.Single

object ProductAccessLayer {

    fun getProductsObservable(token: String): Single<MutableList<Product>> {
        return Single.create { emitter ->
            val response = Proxy.getProducts(token)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to get products"))
            }
            else {

                val list = mutableListOf<Product>()
                response.products.forEach{
                    list.add(Product(it.rating,it.amountType,it.priceType,it.productId,it.ownerName,it.isActive,it.pricePerUnit,it.units,it.description,it.title))
                }
                emitter.onSuccess(list)
            }
        }
    }

    fun AddProductsObservable(token: String, title: String, description: String, pricePerUnit: String,units: String, isActive: Boolean, rating: String, amountType: String, priceType: String): Single<AddProductResponse> {
        return Single.create { emitter ->
            val response = Proxy.addProduct(token, title, description,pricePerUnit,units,isActive,rating,amountType, priceType)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to add product"))
            }
            else {

                val list = mutableListOf<Product>()
                emitter.onSuccess(response)
            }
        }
    }
}