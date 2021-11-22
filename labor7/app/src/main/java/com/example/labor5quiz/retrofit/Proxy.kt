package com.example.labor5quiz.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Proxy {


    private const val BASE_URL = "https://opentdb.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    private val service: RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }

    fun getQuestions(amount: Int) = service.getQuestions(amount).execute().body()

}
