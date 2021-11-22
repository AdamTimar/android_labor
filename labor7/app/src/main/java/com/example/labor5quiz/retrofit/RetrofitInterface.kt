package com.example.labor5quiz.retrofit

import com.example.labor5quiz.retrofit.models.BackEndResponse
import com.example.labor5quiz.retrofit.models.Quiz
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("api.php")
    fun getQuestions(@Query("amount") amount: Int): Call<BackEndResponse<List<Quiz>>>
}